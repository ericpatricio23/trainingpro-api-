package com.eric.apitraining.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "treinos")
public class Treino {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String esporte;

    private String objetivo;

    private String nivel;

    private Integer duracao;

    @Column(columnDefinition = "TEXT")
    private String treinoGerado;

    @CreationTimestamp
    private LocalDateTime dataCriacao;
}