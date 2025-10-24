package app;

import adapter.*;
import api.*;
import exception.AdapterException;
import exception.PublicacaoException;
import factory.SocialMediaFactory;
import model.Conteudo;
import model.Estatisticas;
import strategy.ImmediateStrategy;
import core.GerenciadorMidiaSocial;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) {
        SocialMediaFactory factory = new SocialMediaFactory();
        factory.register("twitter", () -> new TwitterAdapter(new TwitterApi()));
        factory.register("instagram", () -> new InstagramAdapter(new InstagramApi()));
        factory.register("linkedin", () -> new LinkedInAdapter(new LinkedInApi()));
        factory.register("tiktok", () -> new TikTokAdapter(new TikTokApi()));

        GerenciadorMidiaSocial gerenciador = new GerenciadorMidiaSocial(factory);

        try {
            Map<String, String> twConfig = new HashMap<>();
            twConfig.put("apiKey", "meuKey");
            twConfig.put("apiSecret", "meuSecret");
            gerenciador.conectarPlataforma("twitter", twConfig);

            String instagramToken = "meuTokenIG";
            gerenciador.conectarPlataforma("instagram", instagramToken);

            Map<String, String> liConfig = new HashMap<>();
            liConfig.put("clientId", "id");
            liConfig.put("clientSecret", "secret");
            gerenciador.conectarPlataforma("linkedin", liConfig);

            String ttToken = "tokenTikTok";
            gerenciador.conectarPlataforma("tiktok", ttToken);

            Conteudo conteudo = new Conteudo("Olá mundo! Post automatizado", "http://midia.exemplo/img.png");

            Future<String> futureRef = gerenciador.publicar("twitter", conteudo, new ImmediateStrategy(), null);
            String ref = futureRef.get();
            System.out.println("Publicado no Twitter, ref = " + ref);

            LocalDateTime daqui5s = LocalDateTime.now().plusSeconds(5);
            Future<String> refAgend = gerenciador.publicar("linkedin", conteudo, new ImmediateStrategy(), daqui5s);
            System.out.println("Agendado LinkedIn, futura ref (esperando...) -> " + refAgend.get());

            Estatisticas stats = gerenciador.buscarEstatisticas("twitter", ref);
            System.out.println("Estatísticas Twitter: " + stats);

        } catch (AdapterException ae) {
            System.err.println("Erro de integração: " + ae.getMessage());
            ae.printStackTrace();
        } catch (PublicacaoException pe) {
            System.err.println("Erro na publicação: " + pe.getMessage());
            pe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            gerenciador.shutdown();
        }
    }
}
