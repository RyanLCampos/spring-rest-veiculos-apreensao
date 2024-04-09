package com.algaworks.algatransito.api.controller;

import com.algaworks.algatransito.domain.exception.NegocioException;
import com.algaworks.algatransito.domain.model.Proprietario;
import com.algaworks.algatransito.domain.repository.ProprietarioRepository;
import com.algaworks.algatransito.domain.service.RegistroProprietarioService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.*;

@AllArgsConstructor
@RestController
@RequestMapping("/proprietarios")
public class ProprietarioController {


    private final RegistroProprietarioService registroProprietarioService;
    private final ProprietarioRepository proprietarioRepository;

    @GetMapping
    public List<Proprietario> listar(){
        //return proprietarioRepository.findByNome("Ryan Lucas");
        return proprietarioRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Proprietario> buscar(@PathVariable Long id){
        return proprietarioRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Proprietario adicionar(@Valid @RequestBody Proprietario proprietario){
        return registroProprietarioService.salvar(proprietario);
        //        return proprietarioRepository.save(proprietario);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Proprietario> atualizar(@Valid @PathVariable Long id, @RequestBody Proprietario proprietario) {
        if(!proprietarioRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }

        proprietario.setId(id);
        Proprietario proprietarioAtualizado = registroProprietarioService.salvar(proprietario);

        return ResponseEntity.ok(proprietarioAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id){
        if(!proprietarioRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }

        registroProprietarioService.excluir(id);
        return ResponseEntity.noContent().build();
    }

}

