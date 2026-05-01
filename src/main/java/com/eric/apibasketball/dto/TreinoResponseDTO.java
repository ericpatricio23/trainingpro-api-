package com.eric.apibasketball.dto;

import java.time.LocalDateTime;
import java.util.List;

public record TreinoResponseDTO(
        Long id,
        LocalDateTime data,
        Integer duracao,
        String objetivo,
        String nivel,
        List<String> exercicios
) {
}
