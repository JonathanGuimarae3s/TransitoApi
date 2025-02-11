package br.com.jpslg.transito.api.controller;

import br.com.jpslg.transito.api.assembler.VeiculoAssembler;
import br.com.jpslg.transito.api.dto.VeiculoDTO;
import br.com.jpslg.transito.api.input.VeiculoInput;
import br.com.jpslg.transito.domain.model.Veiculo;
import br.com.jpslg.transito.domain.repository.VeiculoRepository;
import br.com.jpslg.transito.domain.service.ApreensaoService;
import br.com.jpslg.transito.domain.service.VeiculoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    private final VeiculoRepository veiculoRepository;
    private final VeiculoService veiculoService;
    private final VeiculoAssembler veiculoAssembler;
    private final ApreensaoService apreensaoService;


    @GetMapping
    public List<VeiculoDTO> listar() {
        return veiculoAssembler.toCollectionDTO(veiculoRepository.findAll());
    }

    @GetMapping("/{veiculoId}")
    public ResponseEntity<VeiculoDTO> buscarPorId(@PathVariable Long veiculoId) {
        return veiculoRepository.findById(veiculoId)
                .map(veiculoAssembler::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VeiculoDTO cadastrar(@Valid @RequestBody VeiculoInput veiculoInput) {
        Veiculo novoVeiculo = veiculoAssembler.toEntity(veiculoInput);
        Veiculo veiculoCadastrado = veiculoService.cadastrar(novoVeiculo);

        return veiculoAssembler.toDTO(veiculoCadastrado);
//        return veiculoAssembler.toDTO(veiculoService.cadastrar(veiculo));*/
    }

    @PutMapping("/{veiculoId}/apreensao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void apreender(@PathVariable Long veiculoId) {
        apreensaoService.apreender(veiculoId);

    }

    @DeleteMapping("/{veiculoId}/apreensao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void liberar(@PathVariable Long veiculoId) {
        apreensaoService.liberar(veiculoId);

    }

}
