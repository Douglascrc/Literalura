package com.alura.literalura.model;

import jakarta.persistence.*;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    @Column(name = "nome", nullable = false)
    private String nome;
    @Column(name = "ano_nascimento", nullable = false)
    private String anoNascimento;
    @Column(name= "ano_morte", nullable = false)
    private String anoMorte;

    public Autor() {
    }

    public Autor(String nome, String anoNascimento, String anoMorte) {
        this.nome = nome;
        this.anoNascimento = anoNascimento;
        this.anoMorte = anoMorte;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return "Autor: " + nome + " (" + anoNascimento + " - " + anoMorte + ")";
    }
}
