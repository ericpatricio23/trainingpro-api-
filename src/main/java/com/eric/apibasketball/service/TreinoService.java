package com.eric.apibasketball.service;

import com.eric.apibasketball.dto.TreinoRequestDTO;
import com.eric.apibasketball.dto.TreinoResponseDTO;
import com.eric.apibasketball.entity.Exercicio;
import com.eric.apibasketball.entity.Treino;
import com.eric.apibasketball.exception.InvalidTreinoException;
import com.eric.apibasketball.exception.TreinoNotFoundException;
import com.eric.apibasketball.repository.ExercicioRepository;
import com.eric.apibasketball.repository.TreinoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TreinoService {

    private final TreinoRepository treinoRepository;
    private final ExercicioRepository exercicioRepository;

    public TreinoService(ExercicioRepository exercicioRepository,
                         TreinoRepository treinoRepository) {
        this.exercicioRepository = exercicioRepository;
        this.treinoRepository = treinoRepository;
    }

    public TreinoResponseDTO salvar(TreinoRequestDTO dto){

        if (dto.exerciciosIds() == null || dto.exerciciosIds().isEmpty()) {
            throw new InvalidTreinoException("Lista de exercícios não pode estar vazia");
        }

        List<Exercicio> exercicios = exercicioRepository.findAllById(dto.exerciciosIds());

        //  Validação: IDs inexistentes
        if (exercicios.size() != dto.exerciciosIds().size()) {
            throw new InvalidTreinoException("Um ou mais exercícios informados não existem");
        }

        //  Criação do treino
        Treino treino = new Treino();
        treino.setData(LocalDateTime.now());
        treino.setDuracao(dto.duracao());
        treino.setObjetivo(dto.objetivo());
        treino.setNivel(dto.nivel());
        treino.setExercicios(exercicios);

        //  Salva
        Treino salvo = treinoRepository.save(treino);

        return toResponseDTO(salvo);
    }
    public Page<TreinoResponseDTO> listar(String objetivo, String nivel, Pageable pageable) {

        Page<Treino> treinos;

        if (objetivo != null && nivel != null) {
            treinos = treinoRepository.findByObjetivoAndNivel(objetivo, nivel, pageable);
        } else if (objetivo != null) {
            treinos = treinoRepository.findByObjetivo(objetivo, pageable);
        } else if (nivel != null) {
            treinos = treinoRepository.findByNivel(nivel, pageable);
        } else {
            treinos = treinoRepository.findAll(pageable);
        }

        return treinos.map(this::toResponseDTO);
    }


    public TreinoResponseDTO buscarPorId(Long id){
        Treino treino = treinoRepository.findById(id)
                .orElseThrow(()-> new TreinoNotFoundException("Treino com ID" + id + " não encontrado"));

        return toResponseDTO(treino);
    }


    public void deletar(Long id){
        Treino treino = treinoRepository.findById(id)
                .orElseThrow(()-> new TreinoNotFoundException("Treino com ID" + id + " não encontrado"));

        treinoRepository.delete(treino);
    }

    private TreinoResponseDTO toResponseDTO (Treino treino){
        return new TreinoResponseDTO(
                treino.getId(),
                treino.getData(),
                treino.getDuracao(),
                treino.getObjetivo(),
                treino.getNivel(),
                treino.getExercicios().stream()
                        .map(Exercicio::getNome)
                        .toList()
        );
    }
}
