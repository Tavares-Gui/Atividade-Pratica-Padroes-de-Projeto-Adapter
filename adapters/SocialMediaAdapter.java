package adapters;

import models.Publicacao;
import models.Estatisticas;
import exceptions.SocialMediaException;

public interface SocialMediaAdapter<TResponse> {
    TResponse publish(Publicacao p) throws SocialMediaException;

    Estatisticas fetchStats(String postId) throws SocialMediaException;

    String platformName();
}
