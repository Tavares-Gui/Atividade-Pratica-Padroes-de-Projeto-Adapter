package factory;

import adapters.*;
import api.twitter.TwitterApi;
import api.instagram.InstagramApi;
import api.linkedin.LinkedInApi;
import api.tiktok.TikTokApi;

import java.util.Map;
import java.util.HashMap;
import java.util.function.Function;

public class SocialMediaFactory {
    private final Map<String, Function<Map<String,String>, SocialMediaAdapter>> registry = new HashMap<>();

    public SocialMediaFactory() {
        registry.put("twitter", cfg -> new TwitterAdapter(new TwitterApi(cfg.get("apiKey"))));
        registry.put("instagram", cfg -> new InstagramAdapter(new InstagramApi(cfg.get("accessToken"))));
        registry.put("linkedin", cfg -> new LinkedInAdapter(new LinkedInApi(cfg.get("clientId"))));
        registry.put("tiktok", cfg -> new TikTokAdapter(new TikTokApi(cfg.get("clientSecret"))));
    }

    public SocialMediaAdapter create(String key, Map<String,String> config) {
        Function<Map<String,String>, SocialMediaAdapter> f = registry.get(key.toLowerCase());
        if (f == null) throw new IllegalArgumentException("Platform not supported: " + key);
        return f.apply(config);
    }

    public void register(String key, Function<Map<String,String>, SocialMediaAdapter> creator) {
        registry.put(key.toLowerCase(), creator);
    }
}
