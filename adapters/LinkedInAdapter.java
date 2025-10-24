package adapters;

import api.linkedin.LinkedInApi;
import models.Publicacao;
import models.Estatisticas;
import exceptions.SocialMediaException;

public class LinkedInAdapter implements SocialMediaAdapter<String> {
    private final LinkedInApi api;
    public LinkedInAdapter(LinkedInApi api) { this.api = api; }

    @Override
    public String publish(Publicacao p) throws SocialMediaException {
        try {
            return api.shareUpdate(p.getTexto());
        } catch (Exception e) {
            throw new SocialMediaException("LinkedInAdapter.publish failed", e);
        }
    }

    @Override
    public Estatisticas fetchStats(String postId) throws SocialMediaException {
        try {
            String j = api.getAnalytics(postId);
            int impressions = Integer.parseInt(j.replaceAll("\\D+", ""));
            Estatisticas s = new Estatisticas();
            s.setImpressoes(impressions);
            return s;
        } catch (Exception e) {
            throw new SocialMediaException("LinkedInAdapter.fetchStats failed", e);
        }
    }

    @Override
    public String platformName() { return "LinkedIn"; }
}
