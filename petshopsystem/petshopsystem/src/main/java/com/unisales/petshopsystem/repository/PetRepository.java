package com.unisales.petshopsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unisales.petshopsystem.domain.Pet;

public interface PetRepository extends JpaRepository<Pet, Long> {
    
}
