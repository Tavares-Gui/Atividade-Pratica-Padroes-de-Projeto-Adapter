package adapters;

import api.twitter.TwitterApi;
import models.Publicacao;
import models.Estatisticas;
import exceptions.SocialMediaException;

public class TwitterAdapter implements SocialMediaAdapter<String> {
    private final TwitterApi api;
    public TwitterAdapter(TwitterApi api) { this.api = api; }

    @Override
    public String publish(Publicacao p) throws SocialMediaException {
        try {
            String id = api.postTweet(p.getTexto());
            return id;
        } catch (Exception e) {
            throw new SocialMediaException("TwitterAdapter.publish failed", e);
        }
    }

    @Override
    public Estatisticas fetchStats(String postId) throws SocialMediaException {
        try {
            String json = api.getStats(postId);
            int likes = Integer.parseInt(json.replaceAll("\\D+", ""));
            Estatisticas stats = new Estatisticas();
            stats.setEngajamento(likes);
            return stats;
        } catch (Exception e) {
            throw new SocialMediaException("TwitterAdapter.fetchStats failed", e);
        }
    }

    @Override
    public String platformName() { return "Twitter"; }
}
