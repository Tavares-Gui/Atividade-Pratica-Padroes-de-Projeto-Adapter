package factory;

import adapter.SocialMediaAdapter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

public class SocialMediaFactory {
    private final Map<String, Supplier<SocialMediaAdapter<?>>> registry = new ConcurrentHashMap<>();

    public void register(String key, Supplier<SocialMediaAdapter<?>> supplier) {
        registry.put(key.toLowerCase(), supplier);
    }

    public boolean isRegistered(String key) {
        return registry.containsKey(key.toLowerCase());
    }

    public SocialMediaAdapter<?> create(String key) {
        Supplier<SocialMediaAdapter<?>> supplier = registry.get(key.toLowerCase());
        if (supplier == null) throw new IllegalArgumentException("Plataforma nao registrada: " + key);
        return supplier.get();
    }
}
