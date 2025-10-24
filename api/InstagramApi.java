package api;

public class InstagramApi {
    public boolean login(String token) {
        return token != null && !token.trim().isEmpty();
    }

    public String createPost(String caption, String mediaUrl) {
        return "ig_" + System.currentTimeMillis();
    }

    public String getInsights(String postId) {
        return "{\"likes\":50,\"comments\":5}";
    }
}
