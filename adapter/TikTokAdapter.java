package adapter;

import api.TikTokApi;
import model.Conteudo;
import model.Estatisticas;

public class TikTokAdapter implements SocialMediaAdapter {
    private TikTokApi tikTokApi = new TikTokApi();

    @Override
    public void publicar(String usuario, Conteudo conteudo) {
        tikTokApi.postarVideo(conteudo.getTexto(), conteudo.getImagemUrl());
    }

    @Override
    public Estatisticas getEstatisticas(String usuario) {
        int[] dados = tikTokApi.obterEstatisticasVideo();
        return new Estatisticas(dados[0], dados[1], dados[2]);
    }
    
}
