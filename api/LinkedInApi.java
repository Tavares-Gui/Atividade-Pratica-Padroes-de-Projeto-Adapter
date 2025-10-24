package api;

public class LinkedInApi {
    public void compartilharPost(String conteudo) {
        System.out.println("[LinkedIn] Compartilhando post: " + conteudo);
    }

    public int[] obterMetricasPost() {
        return new int[]{90, 25, 10};
    }
}
