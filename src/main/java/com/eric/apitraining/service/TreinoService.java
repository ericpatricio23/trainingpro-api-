package com.eric.apitraining.service;
import com.eric.apitraining.client.OpenAIClient;
import com.eric.apitraining.dto.treino.TreinoRequestDTO;
import com.eric.apitraining.dto.treino.TreinoResponseDTO;
import com.eric.apitraining.entity.Treino;
import com.eric.apitraining.exception.treino.InvalidTreinoException;
import com.eric.apitraining.exception.treino.TreinoNotFoundException;
import com.eric.apitraining.repository.TreinoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class TreinoService {

    private final TreinoRepository repository;
    private final OpenAIClient openAIClient;

    public TreinoService(TreinoRepository repository, OpenAIClient openAIClient) {
        this.repository = repository;
        this.openAIClient = openAIClient;
    }

    public TreinoResponseDTO gerar(TreinoRequestDTO dto) {

        String prompt = """
                Crie um treino de %s com foco em %s,
                para nível %s, com duração de %d minutos.
                
                Retorne APENAS JSON no formato:
                {
                  "nome": "string",
                  "exercicios": [
                    { "nome": "string", "tipo": "string", "series": "string", "repeticoes": "string" }
                  ]
                }
                """.formatted(dto.esporte(), dto.objetivo(), dto.nivel(), dto.duracao());

        String respostaIA = openAIClient.gerarTreino(prompt);

        if (respostaIA == null || respostaIA.isBlank()) {
            throw new InvalidTreinoException("A IA não retornou um treino válido");
        }

        Treino treino = new Treino();
        treino.setEsporte(dto.esporte());
        treino.setObjetivo(dto.objetivo());
        treino.setNivel(dto.nivel());
        treino.setDuracao(dto.duracao());
        treino.setTreinoGerado(respostaIA);

        Treino salvo = repository.save(treino);

        return toResponseDTO(salvo);
    }

    public Page<TreinoResponseDTO> listar(String esporte, String objetivo, String nivel, Pageable pageable) {
        return repository.filtrar(esporte, objetivo, nivel, pageable).map(this::toResponseDTO);
    }

    public TreinoResponseDTO buscarPorId(Long id) {
        Treino treino = repository.findById(id)
                .orElseThrow(() -> new TreinoNotFoundException("Treino com ID " + id + " não encontrado"));
        return toResponseDTO(treino);
    }

    private TreinoResponseDTO toResponseDTO(Treino treino) {
        return new TreinoResponseDTO(
                treino.getId(),
                treino.getEsporte(),
                treino.getObjetivo(),
                treino.getNivel(),
                treino.getDuracao(),
                treino.getTreinoGerado(),
                treino.getDataCriacao()
        );
    }

    public void deletarPorID (Long id){
        Treino treino = repository.findById(id)
                .orElseThrow(()->new TreinoNotFoundException("Treino com ID " + id + " não encontrado"));

        repository.delete(treino);
    }

}