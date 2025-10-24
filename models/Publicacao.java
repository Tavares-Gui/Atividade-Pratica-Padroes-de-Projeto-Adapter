package models;

import java.time.Instant;

public class Publicacao {
    private final Conteudo conteudo;
    private final Instant agendamento;
    public Publicacao(Conteudo c, Instant quando) {
        this.conteudo = c;
        this.agendamento = quando;
    }
    public String getTexto() { return conteudo.getTexto(); }
    public byte[] getMedia() { return conteudo.getMedia(); }
    public Instant getAgendamento() { return agendamento; }
}
