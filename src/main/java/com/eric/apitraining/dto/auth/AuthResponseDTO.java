package com.eric.apitraining.dto.auth;

public record AuthResponseDTO(
        String token,
        String nome,
        String email

) {
}
