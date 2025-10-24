package api;

public class LinkedInApi {
    public boolean connect(String clientId, String clientSecret) {
        return clientId != null && clientSecret != null;
    }

    public String share(String titulo, String texto) {
        return "li_" + System.currentTimeMillis();
    }

    public String fetchAnalytics(String shareId) {
        return "{\"views\":200,\"interactions\":20}";
    }
}
