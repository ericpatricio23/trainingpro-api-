package com.eric.apibasketball.service;

import com.eric.apibasketball.dto.ExercicioRequestDTO;
import com.eric.apibasketball.dto.ExercicioResponseDTO;
import com.eric.apibasketball.entity.Exercicio;
import com.eric.apibasketball.entity.TipoExercicio;
import com.eric.apibasketball.exception.ExercicioNotFoundException;
import com.eric.apibasketball.repository.ExercicioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ExercicioService {

    private final ExercicioRepository repository;

    // Injeção de dependência via construtor (boa prática)
    public ExercicioService(ExercicioRepository repository) {
        this.repository = repository;
    }

    // =========================
    //  SALVAR EXERCÍCIO
    // =========================
    public ExercicioResponseDTO salvar(ExercicioRequestDTO dto) {

        //  Conversão DTO → Entity
        Exercicio exercicio = new Exercicio();
        exercicio.setNome(dto.nome());
        exercicio.setTipoExercicio(dto.tipoExercicio());
        exercicio.setIntensidade(dto.intensidade());

        //  Salva no banco
        Exercicio salvo = repository.save(exercicio);

        //  Retorna Entity → DTO
        return toResponseDTO(salvo);
    }

    // =========================
    //  LISTAR TODOS
    // =========================
    public Page<ExercicioResponseDTO> listar(Pageable pageable) {
        return repository.findAll(pageable)
                .map(this::toResponseDTO);
    }


    // =========================
    //  BUSCAR POR ID
    // =========================
    public ExercicioResponseDTO buscarPorId(Long id) {

        // 🔥 Busca e lança exceção se não encontrar
        Exercicio exercicio = repository.findById(id)
                .orElseThrow(() ->
                        new ExercicioNotFoundException("Exercício com ID " + id + " não encontrado")
                );

        return toResponseDTO(exercicio);
    }

    // =========================
    //  BUSCAR POR TIPO
    // =========================
    public List<ExercicioResponseDTO> buscarPorTipo(TipoExercicio tipo){
        return repository.findByTipoExercicio(tipo)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }


    // =========================
    // ATUALIZAR
    // =========================
    public ExercicioResponseDTO atualizar(Long id, ExercicioRequestDTO dto){
        Exercicio exercicio = repository.findById(id)
                .orElseThrow(() -> new ExercicioNotFoundException("Exercicio não encontrado"));

            exercicio.setTipoExercicio(dto.tipoExercicio());
            exercicio.setNome(dto.nome());
            exercicio.setIntensidade(dto.intensidade());

            Exercicio atualizado = repository.save(exercicio);

        return new ExercicioResponseDTO(
                exercicio.getId(),
                exercicio.getNome(),
                exercicio.getTipoExercicio(),
                exercicio.getIntensidade()

        );
    }

    // =========================
    //  DELETAR
    // =========================
    public void deletar(Long id) {

        // 🔥 Garante que existe antes de deletar
        Exercicio exercicio = repository.findById(id)
                .orElseThrow(() ->
                        new ExercicioNotFoundException("Exercício com ID " + id + " não encontrado")
                );

        repository.delete(exercicio);
    }

    // =========================
    //  MAPPER: ENTITY → DTO
    // =========================
    private ExercicioResponseDTO toResponseDTO(Exercicio exercicio) {
        return new ExercicioResponseDTO(
                exercicio.getId(),
                exercicio.getNome(),
                exercicio.getTipoExercicio(),
                exercicio.getIntensidade()
        );
    }
}
