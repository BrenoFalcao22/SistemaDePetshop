package com.unisales.petshopsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unisales.petshopsystem.domain.HistoricoEvolucao;

public interface HistoricoEvolucaoRepository extends JpaRepository<HistoricoEvolucao, Long> {
    
}
