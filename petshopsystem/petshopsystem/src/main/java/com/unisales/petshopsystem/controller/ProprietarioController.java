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

import com.unisales.petshopsystem.domain.Proprietario;
import com.unisales.petshopsystem.repository.ProprietarioRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/proprietarios")
@AllArgsConstructor
public class ProprietarioController {

    private final ProprietarioRepository proprietarioRepository;

    // Listar todos os proprietários
    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<Proprietario> listarProprietarios() {
        return proprietarioRepository.findAll();
    }

    // Buscar proprietário por ID
    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Proprietario pegaProprietario(@PathVariable("id") Long id) {
        return proprietarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proprietário não encontrado"));
    }

    // Salvar novo proprietário
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Proprietario salvaProprietario(@RequestBody Proprietario.Input proprietario) {
        Proprietario novoProprietario = new Proprietario();
        novoProprietario.setName(proprietario.name());
        novoProprietario.setSexo(proprietario.sexo());
        novoProprietario.setCpf(proprietario.cpf());
        novoProprietario.setEmail(proprietario.email());
        novoProprietario.setCelular(proprietario.celular());
        return proprietarioRepository.save(novoProprietario);
    }

    // Atualizar proprietário
    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Proprietario atualizaProprietario(@PathVariable("id") Long id, @RequestBody Proprietario proprietarioAtualizado) {
        return proprietarioRepository.findById(id)
                .map(proprietarioExistente -> {
                    proprietarioExistente.setName(proprietarioAtualizado.getName());
                    proprietarioExistente.setCpf(proprietarioAtualizado.getCpf());
                    proprietarioExistente.setEmail(proprietarioAtualizado.getEmail());
                    proprietarioExistente.setCelular(proprietarioAtualizado.getCelular());
                    return proprietarioRepository.save(proprietarioExistente);
                })
                .orElseThrow(() -> new RuntimeException("Proprietário não encontrado"));
    }

    // Deletar proprietário
    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deletaProprietario(@PathVariable("id") Long id) {
        proprietarioRepository.findById(id).ifPresent(proprietarioRepository::delete);
    }
}
