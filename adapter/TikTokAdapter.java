package adapter;

import api.TikTokApi;
import exception.AdapterException;
import model.Conteudo;
import model.Estatisticas;

import java.util.HashMap;
import java.util.Map;

public class TikTokAdapter implements SocialMediaAdapter<String> {
    private final TikTokApi api;
    private boolean conectado = false;

    public TikTokAdapter(TikTokApi api) { this.api = api; }

    @Override
    public boolean conectar(String config) throws AdapterException {
        try {
            conectado = api.authorize(config);
            return conectado;
        } catch (Exception e) {
            throw new AdapterException("Erro ao conectar TikTok", e);
        }
    }

    @Override
    public String publicar(Conteudo conteudo) throws AdapterException {
        if (!conectado) throw new AdapterException("TikTok nao conectado");
        try {
            return api.uploadVideo(conteudo.getTexto(), conteudo.getMediaUrl());
        } catch (Exception e) {
            throw new AdapterException("Erro ao publicar no TikTok", e);
        }
    }

    @Override
    public Estatisticas buscarEstatisticas(String referencia) throws AdapterException {
        try {
            String raw = api.getStats(referencia);
            Map<String, Object> metrics = new HashMap<>();
            metrics.put("raw", raw);
            return new Estatisticas(plataforma(), referencia, metrics);
        } catch (Exception e) {
            throw new AdapterException("Erro ao buscar estatisticas no TikTok", e);
        }
    }

    @Override
    public String plataforma() { return "tiktok"; }
}
