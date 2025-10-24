package adapters;

import api.tiktok.TikTokApi;
import models.Publicacao;
import models.Estatisticas;
import exceptions.SocialMediaException;

public class TikTokAdapter implements SocialMediaAdapter<String> {
    private final TikTokApi api;
    public TikTokAdapter(TikTokApi api) { this.api = api; }

    @Override
    public String publish(Publicacao p) throws SocialMediaException {
        try {
            byte[] video = p.getMedia();
            return api.uploadVideo(video, p.getTexto());
        } catch (Exception e) {
            throw new SocialMediaException("TikTokAdapter.publish failed", e);
        }
    }

    @Override
    public Estatisticas fetchStats(String postId) throws SocialMediaException {
        try {
            String j = api.metrics(postId);
            int plays = Integer.parseInt(j.replaceAll("\\D+", ""));
            Estatisticas s = new Estatisticas();
            s.setPlays(plays);
            return s;
        } catch (Exception e) {
            throw new SocialMediaException("TikTokAdapter.fetchStats failed", e);
        }
    }

    @Override
    public String platformName() { return "TikTok"; }
}
