import adapter.*;

public class SocialMediaFactory {
    public static SocialMediaAdapterInterface criar(String plataforma) {
        if (plataforma == null) {
            return null;
        }

        switch (plataforma.toLowerCase()) {
            case "instagram":
                return new InstagramAdapter();
            case "linkedin":
                return new LinkedInAdapter();
            case "tiktok":
                return new TikTokAdapter();
            case "twitter":
                return new TwitterAdapter();
            default:
                return null;
        }
    }
}
