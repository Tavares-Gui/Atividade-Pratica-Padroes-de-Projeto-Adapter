package api.instagram;

import java.util.UUID;

public class InstagramApi {
    private final String accessToken;
    public InstagramApi(String accessToken) { this.accessToken = accessToken; }

    public String uploadPhoto(byte[] image, String caption) throws Exception {
        simulateNetwork();
        return "ig-" + UUID.randomUUID().toString();
    }

    public String fetchInsights(String postId) throws Exception {
        simulateNetwork();
        return "{\"views\": " + (100 + (int)(Math.random()*500)) + "}";
    }

    private void simulateNetwork() throws Exception {
        if (Math.random() < 0.02) throw new Exception("Instagram API failure");
    }
}
