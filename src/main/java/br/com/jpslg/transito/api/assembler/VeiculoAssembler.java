package br.com.jpslg.transito.api.assembler;

import br.com.jpslg.transito.api.dto.VeiculoDTO;
import br.com.jpslg.transito.api.input.VeiculoInput;
import br.com.jpslg.transito.domain.model.Veiculo;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class VeiculoAssembler {

    private ModelMapper modelMapper;

    public  Veiculo toEntity(VeiculoInput veiculoInput){
        return modelMapper.map(veiculoInput, Veiculo.class);
    }

    public VeiculoDTO toDTO(Veiculo veiculo) {
        return modelMapper.map(veiculo, VeiculoDTO.class);
    }

    public List<VeiculoDTO> toCollectionDTO(List<Veiculo> veiculos) {
        List<VeiculoDTO> dtos = veiculos.stream()
                .map(this::toDTO)
                .toList();

        return dtos;
    }
}
