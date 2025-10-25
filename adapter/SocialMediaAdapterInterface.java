package adapter;

import model.*;

public interface SocialMediaAdapterInterface {
    public void publicar(String usuario, Conteudo conteudo);
    public Estatisticas getEstatisticas(String usuario);
}
