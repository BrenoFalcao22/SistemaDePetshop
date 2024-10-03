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

import com.unisales.petshopsystem.domain.Pet;
import com.unisales.petshopsystem.domain.Proprietario;
import com.unisales.petshopsystem.repository.PetRepository;
import com.unisales.petshopsystem.repository.ProprietarioRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/pets")
@AllArgsConstructor
public class PetController {

    private final PetRepository petRepository;
    private final ProprietarioRepository proprietarioRepository;

    // Listar todos os pets
    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<Pet> listarPet() {
        return petRepository.findAll();
    }

    // Obter um pet específico por ID
    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Pet pegaPet(@PathVariable("id") Long id) {
        return petRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Animal não encontrado"));
    }

    // Salvar novo pet
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Pet salvaPet(@RequestBody Pet.Input pet) {
        // Verifica se o proprietário informado está cadastrado
        Proprietario proprietario = proprietarioRepository.findById(pet.proprietarioId())
                .orElseThrow(() -> new RuntimeException("Proprietário não encontrado"));

        Pet novoPet = new Pet();
        novoPet.setName(pet.name());
        novoPet.setIdade(pet.idade());
        novoPet.setSexo(pet.sexo());
        novoPet.setEspecie(pet.especie());
        novoPet.setRaca(pet.raca());
        novoPet.setProprietario(proprietario);

        validarPet(novoPet);
        return petRepository.save(novoPet);
    }

    // Atualizar dados de um pet
    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Pet atualizaPet(@PathVariable("id") Long id, @RequestBody Pet petAtualizado) {
        return petRepository.findById(id)
                .map(petExistente -> {
                    // Verifica se o proprietário informado está cadastrado
                    Proprietario proprietario = proprietarioRepository.findById(petAtualizado.getProprietario().getId())
                            .orElseThrow(() -> new RuntimeException("Proprietário não encontrado"));

                    // Atualiza os dados do pet existente com as novas informações
                    petExistente.setName(petAtualizado.getName());
                    petExistente.setIdade(petAtualizado.getIdade());
                    petExistente.setSexo(petAtualizado.getSexo());
                    petExistente.setEspecie(petAtualizado.getEspecie());
                    petExistente.setRaca(petAtualizado.getRaca());
                    petExistente.setProprietario(proprietario);

                    validarPet(petExistente);
                    return petRepository.save(petExistente);
                })
                .orElseThrow(() -> new RuntimeException("Animal não encontrado"));
    }

    // Deletar um pet
    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deletaPet(@PathVariable("id") Long id) {
        petRepository.findById(id).ifPresent(petRepository::delete);
    }

    // Método de validação dos atributos do Pet
    private void validarPet(Pet pet) {
        if (pet.getName() == null || pet.getName().isBlank() || pet.getName().length() > 150) {
            throw new RuntimeException("Nome do animal inválido");
        }
        if (pet.getProprietario() == null) {
            throw new RuntimeException("Proprietário do animal não pode ser nulo");
        }
        if (pet.getIdade() == null) {
            throw new RuntimeException("Idade do animal não pode ser nula");
        }
        if (pet.getSexo() == null || pet.getSexo().length() != 1) {
            throw new RuntimeException("Sexo do animal inválido");
        }
        if (pet.getEspecie() == null || pet.getEspecie().isBlank() || pet.getEspecie().length() > 50) {
            throw new RuntimeException("Espécie do animal inválida");
        }
        if (pet.getRaca() == null || pet.getRaca().isBlank() || pet.getRaca().length() > 50) {
            throw new RuntimeException("Raça do animal inválida");
        }
    }
}
