package adapter;

import model.*;

public interface SocialMediaAdapter {
    public void publicar(String usuario, Conteudo conteudo);
    public Estatisticas getEstatisticas(String usuario);
}
