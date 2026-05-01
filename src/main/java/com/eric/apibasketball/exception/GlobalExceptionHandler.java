package com.eric.apibasketball.exception;

import com.eric.apibasketball.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.method.MethodValidationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ExercicioNotFoundException.class)
        public ResponseEntity<ErrorResponse> handleNotFound(ExercicioNotFoundException ex){

        ErrorResponse erro = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(erro);
    }

    @ExceptionHandler(RegraNegocioException.class)
    public ResponseEntity<ErrorResponse> handleRegraNegocio(RegraNegocioException ex){

        ErrorResponse erro = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(erro);
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<ErrorResponse> handleGeneric(Exception ex){

        ErrorResponse erro = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Erro interno no servidor",
                LocalDateTime.now()
        );
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(erro);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
        String mensagem = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(f -> f.getField() + ": " + f.getDefaultMessage())
                .collect(Collectors.joining(", "));

        ErrorResponse erro = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(), mensagem, LocalDateTime.now()
        );
        return ResponseEntity.badRequest().body(erro);
    }

    @ExceptionHandler(TreinoNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound (TreinoNotFoundException ex){
        ErrorResponse erro = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                LocalDateTime.now()
        );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(erro);
    }

    @ExceptionHandler(InvalidTreinoException.class)
    public ResponseEntity<ErrorResponse> handleInvalidTreino(InvalidTreinoException ex){
        ErrorResponse erro = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                LocalDateTime.now()
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(erro);
    }

}