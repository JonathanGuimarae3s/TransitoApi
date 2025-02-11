package br.com.jpslg.transito.api.assembler;

import br.com.jpslg.transito.api.dto.AutuacaoDTO;
import br.com.jpslg.transito.api.input.AutuacaoInput;
import br.com.jpslg.transito.domain.model.Autuacao;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class AutuacaoAssembler {
    private final ModelMapper modelMapper;

    public AutuacaoDTO toDTO(Autuacao autuacao) {
        return modelMapper.map(autuacao, AutuacaoDTO.class);
    }

    public Autuacao toEntity(AutuacaoInput autuacaoInput) {
        return modelMapper.map(autuacaoInput, Autuacao.class);
    }

    public List<AutuacaoDTO> toDTOList(List<Autuacao> autuacaoList) {
        return autuacaoList.stream().map(this::toDTO).toList();
    }
}
