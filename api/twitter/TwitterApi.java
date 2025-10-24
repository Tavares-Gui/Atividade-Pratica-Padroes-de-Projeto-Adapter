package api.twitter;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class TwitterApi {
    private final String apiKey;
    public TwitterApi(String apiKey) { this.apiKey = apiKey; }

    public String postTweet(String message) throws Exception {
        simulateNetwork();
        return "tw-" + UUID.randomUUID().toString();
    }

    public String getStats(String postId) throws Exception {
        simulateNetwork();
        int likes = ThreadLocalRandom.current().nextInt(0, 1000);
        return "{\"likes\":" + likes + "}";
    }

    private void simulateNetwork() throws Exception {
        if (ThreadLocalRandom.current().nextInt(0, 50) == 0) throw new Exception("Twitter API network error");
    }
}
