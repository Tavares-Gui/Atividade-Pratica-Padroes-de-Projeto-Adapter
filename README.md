# Sistema de Integração com Redes Sociais

Este projeto implementa um **sistema de integração com múltiplas redes sociais (Twitter, Instagram, LinkedIn e TikTok)** utilizando os padrões de projeto **Adapter** e **Factory Method** em Java.  
O objetivo é permitir que diferentes APIs de redes sociais sejam utilizadas de forma unificada e extensível, facilitando a adição de novas plataformas sem modificar o código existente.

---

## Arquitetura e Padrões Utilizados

### Padrão Adapter
O **Adapter** é utilizado para traduzir a interface de cada API de rede social (Twitter, Instagram, LinkedIn e TikTok) para uma interface comum (`SocialMediaAdapter`).  
Assim, o restante do sistema pode interagir com qualquer rede social da mesma forma.

### Padrão Factory
O **Factory (`SocialMediaFactory`)** é responsável por instanciar o adaptador correto com base no nome da plataforma informado.  
Isso elimina a necessidade de criar objetos manualmente e melhora a escalabilidade do sistema.

---

## ⚙️ Funcionamento

1. O usuário cria um objeto `Conteudo`, que representa a publicação (texto e imagem/vídeo).  
2. A **fábrica (`SocialMediaFactory`)** recebe o nome da plataforma e retorna o adaptador correspondente.  
3. Cada adaptador traduz a chamada genérica em chamadas específicas da API da plataforma.  
4. O sistema pode publicar o conteúdo e obter estatísticas de desempenho de cada rede.


