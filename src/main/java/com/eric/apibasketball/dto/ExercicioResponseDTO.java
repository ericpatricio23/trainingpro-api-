package com.eric.apibasketball.dto;

import com.eric.apibasketball.entity.TipoExercicio;

public record ExercicioResponseDTO(
        Long id,
        String nome,
        TipoExercicio tipoExercicio,
        Integer intensidade
) {
}
