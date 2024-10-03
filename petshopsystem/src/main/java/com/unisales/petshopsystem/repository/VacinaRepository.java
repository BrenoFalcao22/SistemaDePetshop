package com.unisales.petshopsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unisales.petshopsystem.domain.Vacina;

public interface VacinaRepository extends JpaRepository<Vacina, Long> {
    
}
