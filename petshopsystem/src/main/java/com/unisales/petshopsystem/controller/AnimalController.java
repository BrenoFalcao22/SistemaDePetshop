package com.unisales.petshopsystem.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unisales.petshopsystem.domain.HistoricoEvolucao;
import com.unisales.petshopsystem.domain.Pet;
import com.unisales.petshopsystem.domain.Proprietario;
import com.unisales.petshopsystem.repository.HistoricoEvolucaoRepository;
import com.unisales.petshopsystem.repository.PetRepository;
import com.unisales.petshopsystem.repository.ProprietarioRepository;
import com.unisales.petshopsystem.repository.UsuarioRepository;
import com.unisales.petshopsystem.repository.VacinaRepository;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/pet")
@AllArgsConstructor
public class AnimalController {
    
    private PetRepository petRepository;

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<Pet> listarPet() {
        return petRepository.findAll();
    }
    
    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Pet pegaPet(@PathVariable("id") Long id) {
        var pet = petRepository.findById(id);

        if (pet.isPresent()) {
            return pet.get();
        } else {
            throw new RuntimeException("Animal não encontrado");
        }
    }
    
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Pet salvaPet(@RequestBody Pet entity) {
        return petRepository.save(entity);
    }

    @PutMapping("/{id}")
    public Pet atualizaPet(@PathVariable("id") Long id, @RequestBody Pet pet) {
        Optional.ofNullable(pet.getName())

    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deletaPet(@PathVariable("id") Long id) {
        petRepository.findById(id).ifPresent(p -> petRepository.delete(p));
    }
    
}
