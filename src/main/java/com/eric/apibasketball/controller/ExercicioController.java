package com.eric.apibasketball.controller;

import com.eric.apibasketball.dto.ExercicioRequestDTO;
import com.eric.apibasketball.dto.ExercicioResponseDTO;
import com.eric.apibasketball.entity.TipoExercicio;
import com.eric.apibasketball.service.ExercicioService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exercicios")
public class ExercicioController {

    private final ExercicioService service;

    public ExercicioController(ExercicioService service) {
        this.service = service;
    }

    // SALVAR
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // 201 CREATED
    public ExercicioResponseDTO salvar(@Valid @RequestBody ExercicioRequestDTO dto) {
        return service.salvar(dto);
    }

    //  LISTAR (paginação + ordenação)
    @GetMapping
    public Page<ExercicioResponseDTO> listar(Pageable pageable) {
        return service.listar(pageable);
    }

    // BUSCAR POR ID
    @GetMapping("/{id}")
    public ExercicioResponseDTO buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    // FILTRO POR TIPO
    @GetMapping("/tipo")
    public List<ExercicioResponseDTO> buscarPorTipo(@RequestParam TipoExercicio tipo) {
        return service.buscarPorTipo(tipo);
    }

    // ATUALIZAR
    @PutMapping("/{id}")
    public ExercicioResponseDTO atualizar(
            @PathVariable Long id,
            @Valid @RequestBody ExercicioRequestDTO dto
    ) {
        return service.atualizar(id, dto);
    }

    //  DELETAR
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204 NO CONTENT
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}
