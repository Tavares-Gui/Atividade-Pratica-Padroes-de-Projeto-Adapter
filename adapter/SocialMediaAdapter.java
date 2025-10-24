package adapter;

import exception.AdapterException;
import model.Conteudo;
import model.Estatisticas;

public interface SocialMediaAdapter<T> {
    boolean conectar(T config) throws AdapterException;

    String publicar(Conteudo conteudo) throws AdapterException;

    Estatisticas buscarEstatisticas(String referencia) throws AdapterException;

    String plataforma();
}
