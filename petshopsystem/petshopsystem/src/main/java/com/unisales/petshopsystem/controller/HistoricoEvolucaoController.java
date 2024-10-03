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

import com.unisales.petshopsystem.domain.HistoricoEvolucao;
import com.unisales.petshopsystem.domain.Pet;
import com.unisales.petshopsystem.repository.HistoricoEvolucaoRepository;
import com.unisales.petshopsystem.repository.PetRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/historico")
@AllArgsConstructor
public class HistoricoEvolucaoController {

  private final HistoricoEvolucaoRepository historicoEvolucaoRepository;
  private final PetRepository petRepository;

  // Listar todos os históricos
  @GetMapping
  @ResponseStatus(value = HttpStatus.OK)
  public List<HistoricoEvolucao> listarHistoricos() {
    return historicoEvolucaoRepository.findAll();
  }

  // Obter histórico por ID
  @GetMapping("/{id}")
  @ResponseStatus(value = HttpStatus.OK)
  public HistoricoEvolucao pegaHistorico(@PathVariable("id") Long id) {
    return historicoEvolucaoRepository.findById(id) 
      .orElseThrow(() -> new RuntimeException("Histórico não encontrado"));
  }

  // Salvar novo histórico
  @PostMapping
  @ResponseStatus(value = HttpStatus.CREATED)
  public HistoricoEvolucao salvaHistorico(@RequestBody HistoricoEvolucao.Input entity) {
    HistoricoEvolucao historico = new HistoricoEvolucao();
    historico.setPeso(entity.peso());
    historico.setAltura(entity.altura());
    Pet pet = petRepository.findById(entity.petId())
      .orElseThrow(() -> new RuntimeException("Pet não encontrado"));
    historico.setPet(pet);

    return historicoEvolucaoRepository.save(historico);
  }

  // Atualizar histórico
  @PutMapping("/{id}")
  @ResponseStatus(value = HttpStatus.OK)
  public HistoricoEvolucao atualizaHistorico(@PathVariable("id") Long id, @RequestBody HistoricoEvolucao historicoAtualizado) {
    return historicoEvolucaoRepository.findById(id)
      .map(historicoExistente -> {
        historicoExistente.setData(historicoAtualizado.getData());
        historicoExistente.setPeso(historicoAtualizado.getPeso());
        historicoExistente.setAltura(historicoAtualizado.getAltura());
        return historicoEvolucaoRepository.save(historicoExistente);
      })
      .orElseThrow(() -> new RuntimeException("Histórico não encontrado"));
  }

  // Deletar histórico
  @DeleteMapping("/{id}")
  @ResponseStatus(value = HttpStatus.NO_CONTENT)
  public void deletaHistorico(@PathVariable("id") Long id) {
    historicoEvolucaoRepository.deleteById(id);
  }
}
