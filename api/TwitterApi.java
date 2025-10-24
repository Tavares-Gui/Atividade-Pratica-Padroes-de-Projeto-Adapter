package api;

public class TwitterApi {
    public boolean authenticate(String apiKey, String apiSecret) {
        return apiKey != null && apiSecret != null;
    }

    public String postTweet(String texto) {
        return "tw_" + System.currentTimeMillis();
    }

    public String getMetrics(String postId) {
        return "{\"likes\":10,\"retweets\":2}";
    }
}
