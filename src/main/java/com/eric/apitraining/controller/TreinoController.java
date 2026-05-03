package com.eric.apitraining.controller;

import com.eric.apitraining.dto.treino.TreinoRequestDTO;
import com.eric.apitraining.dto.treino.TreinoResponseDTO;
import com.eric.apitraining.service.TreinoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/treinos")
public class TreinoController {

    private final TreinoService service;

    public TreinoController(TreinoService service) {
        this.service = service;
    }

    @PostMapping("/gerar")
    @ResponseStatus(HttpStatus.CREATED)
    public TreinoResponseDTO gerar(@Valid @RequestBody TreinoRequestDTO dto) {
        return service.gerar(dto);
    }

    @GetMapping
    public Page<TreinoResponseDTO> listar(
            @RequestParam(required = false) String esporte,
            @RequestParam(required = false) String objetivo,
            @RequestParam(required = false) String nivel,
            Pageable pageable
    ) {
        return service.listar(esporte, objetivo, nivel, pageable);
    }

    @GetMapping("/{id}")
    public TreinoResponseDTO buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}