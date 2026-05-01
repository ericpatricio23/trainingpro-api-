package com.eric.apibasketball.dto;

import com.eric.apibasketball.entity.TipoExercicio;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ExercicioRequestDTO(
        @NotBlank(message = "Nome é obrigatório")
        String nome,

        @NotNull(message = "Tipo é obrigatório")
        TipoExercicio tipoExercicio,

        @NotNull
        @Min(value = 1, message = "minimo é 1")
        @Max(value = 10, message = "maximo é 10 " )
        Integer intensidade

) {
}
