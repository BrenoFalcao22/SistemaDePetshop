package com.unisales.petshopsystem.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "proprietario")
public class Proprietario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, length = 150)
    private String name;
    @Column(nullable = false, length = 1)
    private String sexo;
    @Column(nullable = false, length = 14, unique = true)
    private String cpf;
    @Column(nullable = false, length = 150, unique = true)
    private String email;
    @Column(nullable = false, length = 15, unique = true)
    private String celular;
    
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pet> pet = new ArrayList<>();

    public record Input(String name, String sexo, String cpf, String email, String celular) {}
}
