package com.eric.apibasketball.controller;


import com.eric.apibasketball.dto.TreinoRequestDTO;
import com.eric.apibasketball.dto.TreinoResponseDTO;
import com.eric.apibasketball.service.TreinoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/treinos")
public class TreinoController {

    private final TreinoService service;

    public TreinoController(TreinoService service) {
        this.service = service;
    }

    //  Criar treino
    @PostMapping
    public TreinoResponseDTO salvar(@Valid @RequestBody TreinoRequestDTO dto) {
        return service.salvar(dto);
    }

    //  Listar treinos (com paginação)
    @GetMapping
    public Page<TreinoResponseDTO> listar(
            @RequestParam(required = false) String objetivo,
            @RequestParam(required = false) String nivel,
            Pageable pageable
    ) {
        return service.listar(objetivo, nivel, pageable);
    }

    //  Buscar por ID
    @GetMapping("/{id}")
    public TreinoResponseDTO buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    //  Deletar
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}