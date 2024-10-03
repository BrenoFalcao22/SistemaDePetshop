package com.unisales.petshopsystem.controller;

import java.util.List;
import java.util.Optional;

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

import com.unisales.petshopsystem.domain.Pet;
import com.unisales.petshopsystem.domain.Vacina;
import com.unisales.petshopsystem.repository.PetRepository;
import com.unisales.petshopsystem.repository.VacinaRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/vacina")
@AllArgsConstructor
public class VacinaController {

    private final VacinaRepository vacinaRepository;
    private final PetRepository petRepository;

    // Listar todas as vacinas
    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<Vacina> listarVacinas() {
        return vacinaRepository.findAll();
    }
    
    // Obter vacina por ID
    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Vacina pegaVacina(@PathVariable("id") Long id) {
        return vacinaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Vacina não encontrada"));
    }

    // Salvar nova vacina
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Vacina salvaVacina(@RequestBody Vacina.Input entity) {
        Vacina vacina = new Vacina();
        vacina.setName(entity.name());
        vacina.setDescricao(entity.descricao());
        vacina.setData(entity.data());
        Optional.of(entity.petId()).ifPresent(petId -> {
            // Verifica se o pet existe no sistema
            Pet pet = petRepository.findById(petId)
                    .orElseThrow(() -> new RuntimeException("Pet não encontrado"));
            vacina.setPet(pet);
        });


        return vacinaRepository.save(vacina);
    }

    // Atualizar vacina existente
    @PutMapping("/{id}")
    public Vacina atualizaVacina(@PathVariable("id") Long id, @RequestBody Vacina vacina) {
        return vacinaRepository.findById(id).map(existingVacina -> {
            existingVacina.setName(vacina.getName());
            existingVacina.setDescricao(vacina.getDescricao());
            existingVacina.setData(vacina.getData());
            existingVacina.setPet(vacina.getPet()); // Caso tenha o relacionamento com Pet
            return vacinaRepository.save(existingVacina);
        }).orElseThrow(() -> new RuntimeException("Vacina não encontrada"));
    }

    // Deletar vacina por ID
    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deletaVacina(@PathVariable("id") Long id) {
        vacinaRepository.findById(id).ifPresent(v -> vacinaRepository.delete(v));
    }
}
