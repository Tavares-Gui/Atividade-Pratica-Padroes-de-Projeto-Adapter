package model;

import java.util.Map;

public class Estatisticas {
    private final String plataforma;
    private final String referencia;
    private final Map<String, Object> metrics;

    public Estatisticas(String plataforma, String referencia, Map<String, Object> metrics) {
        this.plataforma = plataforma;
        this.referencia = referencia;
        this.metrics = metrics;
    }

    public String getPlataforma() { return plataforma; }
    public String getReferencia() { return referencia; }
    public Map<String, Object> getMetrics() { return metrics; }

    @Override
    public String toString() {
        return "Estatisticas{" +
                "plataforma='" + plataforma + '\'' +
                ", referencia='" + referencia + '\'' +
                ", metrics=" + metrics +
                '}';
    }
}
