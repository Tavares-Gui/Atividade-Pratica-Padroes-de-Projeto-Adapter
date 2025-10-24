package api;

public class TikTokApi {
    public boolean authorize(String oAuthToken) {
        return oAuthToken != null && !oAuthToken.trim().isEmpty();
    }

    public String uploadVideo(String description, String videoUrl) {
        return "tt_" + System.currentTimeMillis();
    }

    public String getStats(String videoId) {
        return "{\"views\":1000,\"likes\":150}";
    }
}
