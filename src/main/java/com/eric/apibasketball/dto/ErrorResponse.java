package com.eric.apibasketball.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

    private int status;
    private String erro;
    private LocalDateTime data;

}
