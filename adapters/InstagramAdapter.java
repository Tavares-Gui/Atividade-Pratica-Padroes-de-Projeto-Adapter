package adapters;

import api.instagram.InstagramApi;
import models.Publicacao;
import models.Estatisticas;
import exceptions.SocialMediaException;

public class InstagramAdapter implements SocialMediaAdapter<String> {
    private final InstagramApi api;
    public InstagramAdapter(InstagramApi api) { this.api = api; }

    @Override
    public String publish(Publicacao p) throws SocialMediaException {
        try {
            byte[] image = p.getMedia();
            String id = api.uploadPhoto(image, p.getTexto());
            return id;
        } catch (Exception e) {
            throw new SocialMediaException("InstagramAdapter.publish failed", e);
        }
    }

    @Override
    public Estatisticas fetchStats(String postId) throws SocialMediaException {
        try {
            String j = api.fetchInsights(postId);
            int views = Integer.parseInt(j.replaceAll("\\D+", ""));
            Estatisticas s = new Estatisticas();
            s.setVisualizacoes(views);
            return s;
        } catch (Exception e) {
            throw new SocialMediaException("InstagramAdapter.fetchStats failed", e);
        }
    }

    @Override
    public String platformName() { return "Instagram"; }
}
