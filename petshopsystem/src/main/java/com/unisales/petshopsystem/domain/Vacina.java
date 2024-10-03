package com.unisales.petshopsystem.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "vacina")
public class Vacina {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Pet pet;
    @Column(nullable = false, length = 150)
    private String name;
    @Column(length = 500)
    private String descricao;
    @Column(nullable = false)
    private LocalDate data;
    @CreationTimestamp
    private LocalDateTime criacao;
}
