package com.unisales.petshopsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unisales.petshopsystem.domain.Proprietario;

public interface ProprietarioRepository extends JpaRepository<Proprietario, Long> {
    
}
