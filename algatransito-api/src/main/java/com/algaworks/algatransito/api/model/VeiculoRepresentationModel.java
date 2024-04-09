package com.algaworks.algatransito.api.model;

import com.algaworks.algatransito.domain.model.Proprietario;
import com.algaworks.algatransito.domain.model.StatusVeiculo;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class VeiculoRepresentationModel {

    private Long id;
    private ProprietarioResumoModel proprietario;
    private String marca;
    private String modelo;
    private String placa;
    private StatusVeiculo statusVeiculo;
    private OffsetDateTime dataCadastro;
    private OffsetDateTime dataApreensao;
}
