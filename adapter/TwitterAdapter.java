package adapter;

import api.TwitterApi;
import exception.AdapterException;
import model.Conteudo;
import model.Estatisticas;

import java.util.HashMap;
import java.util.Map;

public class TwitterAdapter implements SocialMediaAdapter<Map<String, String>> {
    private final TwitterApi api;
    private boolean conectado = false;

    public TwitterAdapter(TwitterApi api) {
        this.api = api;
    }

    @Override
    public boolean conectar(Map<String, String> config) throws AdapterException {
        try {
            String key = config.get("apiKey");
            String secret = config.get("apiSecret");
            conectado = api.authenticate(key, secret);
            return conectado;
        } catch (Exception e) {
            throw new AdapterException("Erro ao conectar Twitter", e);
        }
    }

    @Override
    public String publicar(Conteudo conteudo) throws AdapterException {
        if (!conectado) throw new AdapterException("Twitter nao conectado");
        try {
            return api.postTweet(conteudo.getTexto());
        } catch (Exception e) {
            throw new AdapterException("Erro ao publicar no Twitter", e);
        }
    }

    @Override
    public Estatisticas buscarEstatisticas(String referencia) throws AdapterException {
        try {
            String raw = api.getMetrics(referencia);
            Map<String, Object> metrics = new HashMap<>();
            metrics.put("raw", raw);
            return new Estatisticas(plataforma(), referencia, metrics);
        } catch (Exception e) {
            throw new AdapterException("Erro ao buscar estatisticas no Twitter", e);
        }
    }

    @Override
    public String plataforma() { return "twitter"; }
}
