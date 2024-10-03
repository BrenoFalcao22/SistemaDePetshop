package com.unisales.petshopsystem.domain;

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
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, length = 150)
    private String name;
    @Column(nullable = false, length = 1)
    private String sexo;
    @Column(nullable = false, length = 10)
    private String senha;
    @Column(nullable = false, length = 150, unique = true)
    private String email;
    @Column(nullable = false, length = 10)
    private String grupo;
}