package api;

public class TikTokApi {
    public void postarVideo(String descricao, String videoUrl) {
        System.out.println("[TikTok] Publicando video: " + videoUrl + " com descricao: " + descricao);
    }

    public int[] obterEstatisticasVideo() {
        return new int[]{500, 120, 60};
    }
}
