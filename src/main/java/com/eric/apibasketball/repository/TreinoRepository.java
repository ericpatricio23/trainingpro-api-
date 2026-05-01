package com.eric.apibasketball.repository;

import com.eric.apibasketball.entity.Treino;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TreinoRepository extends JpaRepository<Treino, Long> {

    Page<Treino> findByObjetivo(String objetivo, Pageable pageable);

    Page<Treino> findByNivel(String nivel, Pageable pageable);

    Page<Treino> findByObjetivoAndNivel(String objetivo, String nivel, Pageable pageable);
}
