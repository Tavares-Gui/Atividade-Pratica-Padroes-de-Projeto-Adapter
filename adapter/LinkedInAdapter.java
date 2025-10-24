package adapter;

import api.LinkedInApi;
import model.Conteudo;
import model.Estatisticas;

public class LinkedInAdapter implements SocialMediaAdapter {
    private LinkedInApi linkedinApi = new LinkedInApi();

    @Override
    public void publicar(String usuario, Conteudo conteudo) {
        linkedinApi.compartilharPost(conteudo.getTexto());
    }

    @Override
    public Estatisticas getEstatisticas(String usuario) {
        int[] dados = linkedinApi.obterMetricasPost();
        return new Estatisticas(dados[0], dados[1], dados[2]);
    }
}
