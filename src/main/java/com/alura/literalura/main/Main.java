package com.alura.literalura.main;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.InputMismatchException;
import java.util.Scanner;
import com.alura.literalura.model.Autor;
import com.fasterxml.jackson.databind.*;

public class Main {
    private final Scanner leitura = new Scanner(System.in);

    public void exibirMenu() {
        var opcao = -1;
        var menu = """
                ___________________________
                 \nEscolha o número da opção desejada:
                   0 - Sair
                   1 - Buscar livro pelo título e salvar
                   2 - Listar livros registrados
                   3 - Listar autores registrados
                   4 - Listar autores vivos em um determinado ano
                   5 - Listar livros em um determinado idioma
                   6 - Buscar autor por nome
                ___________________________   
                """;

        while (opcao != 0) {
            System.out.println(menu);
            try {
                opcao = leitura.nextInt();
                leitura.nextLine(); // Limpar o buffer do scanner

                switch (opcao) {
                    case 0:
                        System.out.println("Saindo do Literalura");
                    case 1:
                        buscaLivroPorTitulo();
                        break;
                    case 2:
                        listarLivros();
                        break;
                    case 3:
                        listarAutores();
                        break;
                    case 4:
                        listarAutoresPorAno();
                        break;
                    case 5:
                        listarLivrosPorIdioma();
                        break;
                    case 6:
                        buscarAutorPorNome();
                        break;
                    default:
                        System.out.println("Opção inválida! Tente novamente.");
                        break;
                }
            } catch(InputMismatchException e) {
                System.out.println("Opção inválida! Digite um número");
                leitura.nextLine();
            }
        }
    }

    private void buscarAutorPorNome() {
        System.out.println("Digite o nome do autor:");
        var nomeAutor = leitura.nextLine().trim();

        HttpClient client = HttpClient.newHttpClient();
        try {

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://gutendex.com/books/?search=" + URLEncoder.encode(nomeAutor, StandardCharsets.UTF_8)))
                    .GET()
                    .build();
            HttpResponse <String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());


            if (response.statusCode() == 200) {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode rootNode = mapper.readTree(response.body());
                JsonNode resultsNode = rootNode.get("results");

                if (resultsNode.size() > 0) {
                    System.out.println("\nAutores encontrados:");

                    for (int i = 0; i < resultsNode.size(); i++) {
                        JsonNode bookNode = resultsNode.get(i);
                        String title = bookNode.get("title").asText();
                        JsonNode authorsNode = bookNode.get("authors");

                        for (int j = 0; j < authorsNode.size(); j++) {
                            JsonNode authorNode = authorsNode.get(j);
                            String name = authorNode.get("name").asText();
                            String birthYear = authorNode.has("birth_year") ?
                                    String.valueOf(authorNode.get("birth_year").asInt()) : "Desconhecido";
                            String deathYear = authorNode.has("death_year") ?
                                    String.valueOf(authorNode.get("death_year").asInt()) : "Presente";

                            Autor autor = new Autor(name, birthYear, deathYear);
                            System.out.println("Livro: " + title);
                            System.out.println(autor);
                            System.out.println("------------------------------");
                        }
                    }
                } else {
                    System.out.println("Nenhum autor encontrado com o nome: " + nomeAutor);
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar autor: " + e.getMessage());
        }

    }

    private void listarLivrosPorIdioma() {
    }

    private void listarAutoresPorAno() {
    }

    private void listarAutores() {
    }

    private void listarLivros() {
    }

    private void buscaLivroPorTitulo() {
    }
}
