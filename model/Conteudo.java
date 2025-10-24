package model;

public class Conteudo {
    private String texto;
    private String imagemUrl;

    public Conteudo(String texto, String imagemUrl) {
        this.texto = texto;
        this.imagemUrl = imagemUrl;
    }

    public String getTexto() {
        return texto;
    }

    public String getImagemUrl() {
        return imagemUrl;
    }
}