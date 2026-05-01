package com.eric.apibasketball.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record TreinoRequestDTO(

        @NotNull(message = "Duração é obrigatória")
        @Min(value = 1, message = "Duração deve ser maior que 0")
        Integer duracao,

        @NotBlank(message = "Objetivo é obrigatorio")
        String objetivo,

        @NotBlank(message = "Nível é obrigatório")
        String nivel,

        @NotEmpty(message = "Lista de exercicios não pode estar vazia")
        List<Long> exerciciosIds
) {
}
