package adapter;

import api.InstagramApi;
import exception.AdapterException;
import model.Conteudo;
import model.Estatisticas;

import java.util.HashMap;
import java.util.Map;

public class InstagramAdapter implements SocialMediaAdapter<String> {
    private final InstagramApi api;
    private boolean conectado = false;
    private String token;

    public InstagramAdapter(InstagramApi api) {
        this.api = api;
    }

    @Override
    public boolean conectar(String config) throws AdapterException {
        try {
            token = config;
            conectado = api.login(token);
            return conectado;
        } catch (Exception e) {
            throw new AdapterException("Erro ao conectar Instagram", e);
        }
    }

    @Override
    public String publicar(Conteudo conteudo) throws AdapterException {
        if (!conectado) throw new AdapterException("Instagram nao conectado");
        try {
            return api.createPost(conteudo.getTexto(), conteudo.getMediaUrl());
        } catch (Exception e) {
            throw new AdapterException("Erro ao publicar no Instagram", e);
        }
    }

    @Override
    public Estatisticas buscarEstatisticas(String referencia) throws AdapterException {
        try {
            String raw = api.getInsights(referencia);
            Map<String, Object> metrics = new HashMap<>();
            metrics.put("raw", raw);
            return new Estatisticas(plataforma(), referencia, metrics);
        } catch (Exception e) {
            throw new AdapterException("Erro ao buscar estatisticas no Instagram", e);
        }
    }

    @Override
    public String plataforma() { return "instagram"; }
}
