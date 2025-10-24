package api.linkedin;

import java.util.UUID;

public class LinkedInApi {
    private final String clientId;
    public LinkedInApi(String clientId) { this.clientId = clientId; }

    public String shareUpdate(String text) throws Exception {
        simulateNetwork();
        return "li-" + UUID.randomUUID().toString();
    }

    public String getAnalytics(String updateId) throws Exception {
        simulateNetwork();
        return "{\"impressions\": " + (100 + (int)(Math.random()*1000)) + "}";
    }

    private void simulateNetwork() throws Exception {
        if (Math.random() < 0.03) throw new Exception("LinkedIn API error");
    }
}
