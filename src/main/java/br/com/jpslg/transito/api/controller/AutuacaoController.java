package br.com.jpslg.transito.api.controller;

import br.com.jpslg.transito.api.assembler.AutuacaoAssembler;
import br.com.jpslg.transito.api.dto.AutuacaoDTO;
import br.com.jpslg.transito.api.input.AutuacaoInput;
import br.com.jpslg.transito.domain.model.Autuacao;
import br.com.jpslg.transito.domain.model.Veiculo;
import br.com.jpslg.transito.domain.service.AutuacaoService;
import br.com.jpslg.transito.domain.service.VeiculoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/veiculos/{veiculoId}/autuacoes")
public class AutuacaoController {

    private final AutuacaoService autuacaoService;

    private final AutuacaoAssembler autuacaoAssembler;
    private final VeiculoService veiculoService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AutuacaoDTO registrarAutuacao(@PathVariable Long veiculoId,
                                         @Valid @RequestBody AutuacaoInput autuacaoInput) {
        Autuacao autuacao = autuacaoAssembler.toEntity(autuacaoInput);
        autuacao = autuacaoService.registrar(veiculoId, autuacao);


        return autuacaoAssembler.toDTO(autuacao);
    }

    @GetMapping
    public List<AutuacaoDTO> listarAutuacao(@PathVariable Long veiculoId) {

        Veiculo veiculo = veiculoService.buscar(veiculoId);

        return autuacaoAssembler.toDTOList(veiculo.getAutuacaoList());
    }

}
