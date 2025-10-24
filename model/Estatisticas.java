package model;

public class Estatisticas {
    private int curtidas;
    private int comentarios;
    private int compartilhamentos;

    public Estatisticas(int curtidas, int comentarios, int compartilhamentos) {
        this.curtidas = curtidas;
        this.comentarios = comentarios;
        this.compartilhamentos = compartilhamentos;
    }

    @Override
    public String toString() {
        return "Curtidas: " + curtidas + ", Comentarios: " + comentarios + ", Compartilhamentos: " + compartilhamentos;
    }
}
