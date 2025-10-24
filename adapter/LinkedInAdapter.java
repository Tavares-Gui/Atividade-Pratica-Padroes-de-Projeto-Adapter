package adapter;

import api.LinkedInApi;
import exception.AdapterException;
import model.Conteudo;
import model.Estatisticas;

import java.util.HashMap;
import java.util.Map;

public class LinkedInAdapter implements SocialMediaAdapter<Map<String, String>> {
    private final LinkedInApi api;
    private boolean conectado = false;

    public LinkedInAdapter(LinkedInApi api) {
        this.api = api;
    }

    @Override
    public boolean conectar(Map<String, String> config) throws AdapterException {
        try {
            conectado = api.connect(config.get("clientId"), config.get("clientSecret"));
            return conectado;
        } catch (Exception e) {
            throw new AdapterException("Erro ao conectar LinkedIn", e);
        }
    }

    @Override
    public String publicar(Conteudo conteudo) throws AdapterException {
        if (!conectado) throw new AdapterException("LinkedIn nao conectado");
        try {
            return api.share("Publicacao", conteudo.getTexto());
        } catch (Exception e) {
            throw new AdapterException("Erro ao publicar no LinkedIn", e);
        }
    }

    @Override
    public Estatisticas buscarEstatisticas(String referencia) throws AdapterException {
        try {
            String raw = api.fetchAnalytics(referencia);
            Map<String, Object> metrics = new HashMap<>();
            metrics.put("raw", raw);
            return new Estatisticas(plataforma(), referencia, metrics);
        } catch (Exception e) {
            throw new AdapterException("Erro ao buscar estatisticas no LinkedIn", e);
        }
    }

    @Override
    public String plataforma() { return "linkedin"; }
}
