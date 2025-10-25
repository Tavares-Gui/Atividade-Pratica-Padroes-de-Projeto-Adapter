import adapter.SocialMediaAdapterInterface;
import model.Conteudo;

public class Main {
    public static void main(String[] args) {
        Conteudo conteudo = new Conteudo("Novo video", "video.mp4");

        SocialMediaAdapterInterface instagram = SocialMediaFactory.criar("instagram");
        SocialMediaAdapterInterface linkedin = SocialMediaFactory.criar("linkedin");
        SocialMediaAdapterInterface tiktok = SocialMediaFactory.criar("tiktok");
        SocialMediaAdapterInterface twitter = SocialMediaFactory.criar("twitter");

        instagram.publicar("TESTE-USUARIO", conteudo);
        linkedin.publicar("TESTE-USUARIO", conteudo);
        tiktok.publicar("TESTE-USUARIO", conteudo);
        twitter.publicar("TESTE-USUARIO", conteudo);

        System.out.println("\nEstatisticas");
        System.out.println("Instagram > " + instagram.getEstatisticas("TESTE-USUARIO"));
        System.out.println("LinkedIn > " + linkedin.getEstatisticas("TESTE-USUARIO"));
        System.out.println("TikTok > " + tiktok.getEstatisticas("TESTE-USUARIO"));
        System.out.println("Twitter > " + twitter.getEstatisticas("TESTE-USUARIO"));
    }
}