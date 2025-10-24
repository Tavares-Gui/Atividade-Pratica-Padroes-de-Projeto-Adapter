package api;

public class TwitterApi {
    public void postarTweet(String mensagem) {
        System.out.println("[Twitter] Postando tweet: " + mensagem);
    }
    
    public int[] obterEstatisticasTweet() {
        return new int[]{120, 40, 15};
    }
}
