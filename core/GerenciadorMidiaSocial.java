package core;

import adapters.SocialMediaAdapter;
import models.Publicacao;
import models.Estatisticas;
import strategy.SchedulingStrategy;
import exceptions.SocialMediaException;

import java.util.concurrent.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class GerenciadorMidiaSocial {
    private final ConcurrentMap<String, SocialMediaAdapter> adapters = new ConcurrentHashMap<>();
    private final ExecutorService executor;
    private final AtomicInteger publishedCount = new AtomicInteger(0);
    private SchedulingStrategy schedulingStrategy;

    public GerenciadorMidiaSocial(int threads, SchedulingStrategy schedulingStrategy) {
        this.executor = Executors.newFixedThreadPool(threads);
        this.schedulingStrategy = schedulingStrategy;
    }

    public void registerPlatform(String key, SocialMediaAdapter adapter) {
        adapters.put(key.toLowerCase(), adapter);
    }

    public void unregisterPlatform(String key) {
        adapters.remove(key.toLowerCase());
    }

    public Set<String> registeredPlatforms() { return Collections.unmodifiableSet(adapters.keySet()); }

    public Map<String, Response<String>> publishToAll(Publicacao p) {
        Map<String, Future<Response<String>>> futures = new HashMap<>();
        for (Map.Entry<String, SocialMediaAdapter> e : adapters.entrySet()) {
            String platform = e.getKey();
            SocialMediaAdapter adapter = e.getValue();

            if (!schedulingStrategy.shouldPublishNow(p)) {
                futures.put(platform, CompletableFuture.completedFuture(Response.fail("Scheduled for later")));
                continue;
            }

            Future<Response<String>> f = executor.submit(() -> {
                try {
                    Object id = adapter.publish(p);
                    publishedCount.incrementAndGet();
                    return Response.ok(id.toString());
                } catch (SocialMediaException ex) {
                    return Response.fail(ex.getMessage());
                } catch (Exception ex) {
                    return Response.fail("Unexpected: " + ex.getMessage());
                }
            });
            futures.put(platform, f);
        }

        Map<String, Response<String>> results = new HashMap<>();
        for (Map.Entry<String, Future<Response<String>>> entry : futures.entrySet()) {
            String platform = entry.getKey();
            Future<Response<String>> f = entry.getValue();
            try {
                Response<String> r = f.get(10, TimeUnit.SECONDS);
                results.put(platform, r);
            } catch (TimeoutException te) {
                results.put(platform, Response.fail("Timeout while publishing"));
            } catch (Exception ex) {
                results.put(platform, Response.fail("Publish error: " + ex.getMessage()));
            }
        }
        return results;
    }

    public Map<String, Response<Estatisticas>> fetchStats(Collection<String> platformKeys, String postId) {
        Map<String, Response<Estatisticas>> out = new HashMap<>();
        for (String key : platformKeys) {
            SocialMediaAdapter adapter = adapters.get(key.toLowerCase());
            if (adapter == null) { out.put(key, Response.fail("Platform not registered")); continue; }
            try {
                Estatisticas s = adapter.fetchStats(postId);
                out.put(key, Response.ok(s));
            } catch (SocialMediaException e) {
                out.put(key, Response.fail(e.getMessage()));
            } catch (Exception e) {
                out.put(key, Response.fail("Unexpected: " + e.getMessage()));
            }
        }
        return out;
    }

    public int getPublishedCount() { return publishedCount.get(); }

    public void shutdown() {
        executor.shutdown();
    }
}
