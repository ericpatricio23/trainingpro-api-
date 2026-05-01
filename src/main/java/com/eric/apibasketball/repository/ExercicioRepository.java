package com.eric.apibasketball.repository;

import com.eric.apibasketball.entity.Exercicio;
import com.eric.apibasketball.entity.TipoExercicio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExercicioRepository extends JpaRepository <Exercicio,Long> {

    List<Exercicio> findByTipoExercicio(TipoExercicio tipo);
}
