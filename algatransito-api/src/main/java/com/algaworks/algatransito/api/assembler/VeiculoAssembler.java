package com.algaworks.algatransito.api.assembler;

import com.algaworks.algatransito.api.model.VeiculoRepresentationModel;
import com.algaworks.algatransito.api.model.input.VeiculoInput;
import com.algaworks.algatransito.domain.model.Veiculo;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class VeiculoAssembler {

    private final ModelMapper modelMapper;

    public Veiculo toEntity(VeiculoInput veiculoInput){
        return modelMapper.map(veiculoInput, Veiculo.class);
    }

    public VeiculoRepresentationModel toModel(Veiculo veiculo){
        return modelMapper.map(veiculo, VeiculoRepresentationModel.class);
    }

    public List<VeiculoRepresentationModel> toCollectionModel(List<Veiculo> veiculos){
        return veiculos.stream()
                .map(this::toModel)
                .toList();
    }
}
