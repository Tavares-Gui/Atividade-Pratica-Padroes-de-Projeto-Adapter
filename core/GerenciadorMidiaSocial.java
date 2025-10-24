package core;

import adapter.SocialMediaAdapter;
import exception.AdapterException;
import exception.PublicacaoException;
import factory.SocialMediaFactory;
import model.Conteudo;
import model.Estatisticas;
import model.Publicacao;
import strategy.SchedulingStrategy;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.*;

public class GerenciadorMidiaSocial {
    private final SocialMediaFactory factory;
    private final ConcurrentMap<String, SocialMediaAdapter<?>> adapters = new ConcurrentHashMap<>();
    private final ExecutorService executor;
    private final ConcurrentMap<String, Object> configs = new ConcurrentHashMap<>();

    public GerenciadorMidiaSocial(SocialMediaFactory factory) {
        this.factory = factory;
        this.executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    }

    public synchronized <C> void conectarPlataforma(String plataforma, C config) throws AdapterException {
        Objects.requireNonNull(plataforma);
        if (!factory.isRegistered(plataforma)) {
            throw new AdapterException("Plataforma não registrada na factory: " + plataforma);
        }
        SocialMediaAdapter<C> adapter = (SocialMediaAdapter<C>) factory.create(plataforma);
        boolean ok = adapter.conectar(config);
        if (!ok) throw new AdapterException("Falha na conexão com " + plataforma);
        adapters.put(plataforma.toLowerCase(), adapter);
        configs.put(plataforma.toLowerCase(), config);
    }

    public Future<String> publicar(String plataforma, Conteudo conteudo, SchedulingStrategy strategy, LocalDateTime agendadaPara) throws PublicacaoException {
        SocialMediaAdapter<?> adapter = adapters.get(plataforma.toLowerCase());
        if (adapter == null) throw new PublicacaoException("Adapter não conectado para: " + plataforma);

        Publicacao publicacao = new Publicacao(plataforma.toLowerCase(), conteudo, agendadaPara);

        if (agendadaPara != null && agendadaPara.isAfter(LocalDateTime.now())) {
            long delayMillis = java.time.Duration.between(LocalDateTime.now(), agendadaPara).toMillis();
            return scheduleWithDelay(() -> doPublish(adapter, publicacao), delayMillis);
        } else {
            return executor.submit(() -> doPublish(adapter, publicacao));
        }
    }

    private Future<String> scheduleWithDelay(Callable<String> task, long delayMillis) {
        ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
        CompletableFuture<String> promise = new CompletableFuture<>();
        ses.schedule(() -> {
            try {
                String result = task.call();
                promise.complete(result);
            } catch (Throwable t) {
                promise.completeExceptionally(t);
            } finally {
                ses.shutdown();
            }
        }, delayMillis, TimeUnit.MILLISECONDS);
        return promise;
    }

    private String doPublish(SocialMediaAdapter<?> adapter, Publicacao publicacao) throws PublicacaoException {
        try {
            String ref = adapter.publicar(publicacao.getConteudo());
            return ref;
        } catch (Exception e) {
            throw new PublicacaoException("Erro ao publicar em " + publicacao.getPlataforma(), e);
        }
    }

    public Estatisticas buscarEstatisticas(String plataforma, String referencia) throws AdapterException {
        SocialMediaAdapter<?> adapter = adapters.get(plataforma.toLowerCase());
        if (adapter == null) throw new AdapterException("Adapter não conectado para: " + plataforma);
        return adapter.buscarEstatisticas(referencia);
    }

    public void shutdown() {
        executor.shutdown();
    }
}
