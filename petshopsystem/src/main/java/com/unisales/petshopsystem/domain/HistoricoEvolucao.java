package com.unisales.petshopsystem.domain;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "historico_evolucao")
public class HistoricoEvolucao {

    private Pet pet;
    @Column(nullable = false)
    private double peso;
    @Column(nullable = false)
    private double altura;
    @CreationTimestamp
    private LocalDateTime data;
}
