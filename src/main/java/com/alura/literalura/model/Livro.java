package com.alura.literalura.model;

import jakarta.persistence.*;

@Entity
@Table(name="livros")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    @Column(name = "titulo",nullable = false, unique = true)
    private String titulo;
    @Column(name = "autor",nullable = false)
    private String autor;
    @Column(nullable = false)
    private String genero;
    private String resumo;

    public Livro(String titulo, String autor, String genero, String resumo) {
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.resumo = resumo;
    }

    public String getTitulo() {
        return titulo;
    }
}
