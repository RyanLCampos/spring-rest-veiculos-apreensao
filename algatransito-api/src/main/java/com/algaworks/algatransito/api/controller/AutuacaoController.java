package com.algaworks.algatransito.api.controller;

import com.algaworks.algatransito.api.assembler.AutuacaoAssembler;
import com.algaworks.algatransito.api.model.AutuacaoModel;
import com.algaworks.algatransito.api.model.input.AutuacaoInput;
import com.algaworks.algatransito.domain.model.Autuacao;
import com.algaworks.algatransito.domain.repository.AutuacaoRepository;
import com.algaworks.algatransito.domain.service.RegistroAutuacaoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/veiculos/{veiculoId}/autuacoes")
public class AutuacaoController {

    private final RegistroAutuacaoService registroAutuacaoService;
    private final AutuacaoRepository autuacaoRepository;
    private AutuacaoAssembler autuacaoAssembler;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AutuacaoModel cadastrar(@PathVariable Long veiculoId,
                                   @Valid @RequestBody AutuacaoInput autuacaoInput){

        Autuacao autuacaoNova = autuacaoAssembler.toEntity(autuacaoInput);

        Autuacao autuacaoRegistrada = registroAutuacaoService.registrar(veiculoId, autuacaoNova);

        return autuacaoAssembler.toModel(autuacaoRegistrada);
    }

    @GetMapping
    public List<AutuacaoModel> listar(@PathVariable Long veiculoId){
        List<Autuacao> autuacoes = registroAutuacaoService.buscarAutuacoes(veiculoId);

        return autuacaoAssembler.toCollectionModel(autuacoes);
    }


}
