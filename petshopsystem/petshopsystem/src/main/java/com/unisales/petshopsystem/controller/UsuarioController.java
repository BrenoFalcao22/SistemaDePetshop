package com.unisales.petshopsystem.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.unisales.petshopsystem.domain.Usuario;
import com.unisales.petshopsystem.repository.UsuarioRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/usuarios")
@AllArgsConstructor
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    // Listar todos os usuários
    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    // Buscar usuário por ID
    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Usuario pegaUsuario(@PathVariable("id") Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    // Salvar novo usuário
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Usuario salvaUsuario(@RequestBody Usuario.Input usuario) {
        Usuario novoUsuario = new Usuario();
        novoUsuario.setName(usuario.name());
        novoUsuario.setEmail(usuario.email());
        novoUsuario.setSenha(usuario.senha());
        novoUsuario.setGrupo(usuario.grupo());
        return usuarioRepository.save(novoUsuario);
    }

    // Atualizar usuário
    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Usuario atualizaUsuario(@PathVariable("id") Long id, @RequestBody Usuario usuarioAtualizado) {
        return usuarioRepository.findById(id)
                .map(usuarioExistente -> {
                    usuarioExistente.setName(usuarioAtualizado.getName());
                    usuarioExistente.setEmail(usuarioAtualizado.getEmail());
                    usuarioExistente.setSenha(usuarioAtualizado.getSenha());
                    return usuarioRepository.save(usuarioExistente);
                })
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    // Deletar usuário
    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deletaUsuario(@PathVariable("id") Long id) {
        usuarioRepository.findById(id).ifPresent(usuarioRepository::delete);
    }
}
