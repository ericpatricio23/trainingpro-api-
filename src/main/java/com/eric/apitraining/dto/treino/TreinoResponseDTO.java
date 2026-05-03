package com.eric.apitraining.dto.treino;

import java.time.LocalDateTime;

public record TreinoResponseDTO(
        Long id,
        String esporte,
        String objetivo,
        String nivel,
        Integer duracao,
        String treinoGerado,
        LocalDateTime dataCriacao
) {}