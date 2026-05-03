package com.eric.apitraining.repository;

import com.eric.apitraining.entity.Treino;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TreinoRepository extends JpaRepository<Treino, Long> {

    @Query("""
            SELECT t FROM Treino t
            WHERE (:esporte IS NULL OR t.esporte = :esporte)
            AND (:objetivo IS NULL OR t.objetivo = :objetivo)
            AND (:nivel IS NULL OR t.nivel = :nivel)
            """)
    Page<Treino> filtrar(
            @Param("esporte") String esporte,
            @Param("objetivo") String objetivo,
            @Param("nivel") String nivel,
            Pageable pageable
    );
}