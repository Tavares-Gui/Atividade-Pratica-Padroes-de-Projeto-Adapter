package adapter;

import api.InstagramApi;
import model.Conteudo;
import model.Estatisticas;

public class InstagramAdapter implements SocialMediaAdapterInterface {
    private InstagramApi instagramApi = new InstagramApi();

    @Override
    public void publicar(String usuario, Conteudo conteudo) {
        instagramApi.publicarFoto(conteudo.getTexto(), conteudo.getImagemUrl());
    }

    @Override
    public Estatisticas getEstatisticas(String usuario) {
        int[] dados = instagramApi.obterEstatisticasPost();
        return new Estatisticas(dados[0], dados[1], dados[2]);
    }
}
