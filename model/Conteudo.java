package model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Conteudo {
    private final String texto;
    private final String mediaUrl;
    private final LocalDateTime criadoEm;

    public Conteudo(String texto, String mediaUrl) {
        this.texto = texto;
        this.mediaUrl = mediaUrl;
        this.criadoEm = LocalDateTime.now();
    }

    public String getTexto() { return texto; }
    public String getMediaUrl() { return mediaUrl; }
    public LocalDateTime getCriadoEm() { return criadoEm; }

    @Override
    public String toString() {
        return "Conteudo{" +
                "texto='" + texto + '\'' +
                ", mediaUrl='" + mediaUrl + '\'' +
                ", criadoEm=" + criadoEm +
                '}';
    }
}
