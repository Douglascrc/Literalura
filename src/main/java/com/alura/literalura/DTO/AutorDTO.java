package com.alura.literalura.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AutorDTO(
        @JsonProperty("name")
        String nome,

        @JsonProperty("birth_year")
        int anoNascimento,

        @JsonProperty("death_year")
        int anoMorte
) {}
