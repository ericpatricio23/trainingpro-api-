package com.eric.apibasketball.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Treinos")
public class Treino {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime data;

    private Integer duracao;

    private String objetivo;

    private String nivel;

    @ManyToMany
    @JoinTable (
            name = "treino_exercicio",
            joinColumns = @JoinColumn (name = "treino_id"),
            inverseJoinColumns = @JoinColumn(name = "exercicio_id")
    )

    private List<Exercicio> exercicios;

}
