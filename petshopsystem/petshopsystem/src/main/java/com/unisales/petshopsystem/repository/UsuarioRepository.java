package com.unisales.petshopsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unisales.petshopsystem.domain.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
}
