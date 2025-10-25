package adapter;

import api.TwitterApi;
import model.Conteudo;
import model.Estatisticas;

public class TwitterAdapter implements SocialMediaAdapterInterface {
    private TwitterApi twitterApi = new TwitterApi();

    @Override
    public void publicar(String usuario, Conteudo conteudo) {
        twitterApi.postarTweet(conteudo.getTexto());
    }

    @Override
    public Estatisticas getEstatisticas(String usuario) {
        int[] dados = twitterApi.obterEstatisticasTweet();
        return new Estatisticas(dados[0], dados[1], dados[2]);
    }
}
