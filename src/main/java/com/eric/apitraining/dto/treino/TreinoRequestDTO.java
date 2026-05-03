package com.eric.apitraining.dto.treino;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TreinoRequestDTO(

        @NotBlank(message = "Esporte é obrigatório")
        String esporte,

        @NotBlank(message = "Objetivo é obrigatório")
        String objetivo,

        @NotBlank(message = "Nível é obrigatório")
        String nivel,

        @NotNull(message = "Duração é obrigatória")
        @Min(value = 1, message = "Duração deve ser maior que 0")
        Integer duracao
) {}