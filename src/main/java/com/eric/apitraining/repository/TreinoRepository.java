package com.eric.apitraining.repository;

import com.eric.apitraining.entity.Treino;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TreinoRepository extends JpaRepository<Treino, Long> {

    Page<Treino> findByEsporte(String esporte, Pageable pageable);

    Page<Treino> findByObjetivo(String objetivo, Pageable pageable);

    Page<Treino> findByNivel(String nivel, Pageable pageable);

    Page<Treino> findByEsporteAndObjetivoAndNivel(String esporte, String objetivo, String nivel, Pageable pageable);
}