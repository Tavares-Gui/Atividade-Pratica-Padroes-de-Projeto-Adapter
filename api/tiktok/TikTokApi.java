package api.tiktok;

import java.util.UUID;

public class TikTokApi {
    private final String clientSecret;
    public TikTokApi(String clientSecret) { this.clientSecret = clientSecret; }

    public String uploadVideo(byte[] video, String description) throws Exception {
        simulateNetwork();
        return "tt-" + UUID.randomUUID().toString();
    }

    public String metrics(String id) throws Exception {
        simulateNetwork();
        return "{\"plays\": " + (200 + (int)(Math.random()*2000)) + "}";
    }

    private void simulateNetwork() throws Exception {
        if (Math.random() < 0.02) throw new Exception("TikTok rate limit");
    }
}
