package models;

public class Conteudo {
    private String texto;
    private byte[] media;
    public Conteudo(String texto) { this(texto, null); }
    public Conteudo(String texto, byte[] media) { this.texto = texto; this.media = media; }
    public String getTexto() { return texto; }
    public byte[] getMedia() { return media; }
}
