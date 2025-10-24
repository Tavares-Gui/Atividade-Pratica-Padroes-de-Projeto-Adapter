package model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Publicacao {
    private final String plataforma;
    private final Conteudo conteudo;
    private final LocalDateTime agendadaPara;
    private final String referenciaExterna;
    private final LocalDateTime criadoEm;

    public Publicacao(String plataforma, Conteudo conteudo, LocalDateTime agendadaPara) {
        this.plataforma = plataforma;
        this.conteudo = conteudo;
        this.agendadaPara = agendadaPara;
        this.criadoEm = LocalDateTime.now();
        this.referenciaExterna = null;
    }

    public Publicacao(String plataforma, Conteudo conteudo, LocalDateTime agendadaPara, String referenciaExterna) {
        this.plataforma = plataforma;
        this.conteudo = conteudo;
        this.agendadaPara = agendadaPara;
        this.referenciaExterna = referenciaExterna;
        this.criadoEm = LocalDateTime.now();
    }

    public String getPlataforma() { return plataforma; }
    public Conteudo getConteudo() { return conteudo; }
    public LocalDateTime getAgendadaPara() { return agendadaPara; }
    public String getReferenciaExterna() { return referenciaExterna; }
    public LocalDateTime getCriadoEm() { return criadoEm; }

    @Override
    public String toString() {
        return "Publicacao{" +
                "plataforma='" + plataforma + '\'' +
                ", conteudo=" + conteudo +
                ", agendadaPara=" + agendadaPara +
                ", referenciaExterna='" + referenciaExterna + '\'' +
                ", criadoEm=" + criadoEm +
                '}';
    }
}
