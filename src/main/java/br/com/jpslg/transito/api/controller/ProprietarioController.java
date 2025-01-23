package br.com.jpslg.transito.api.controller;

import br.com.jpslg.transito.domain.model.Proprietario;
import br.com.jpslg.transito.domain.repository.ProprietarioRepository;
import br.com.jpslg.transito.service.ProprietarioService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/proprietarios")
public class ProprietarioController {


    private final ProprietarioRepository proprietarioRepository;
    private final ProprietarioService proprietarioService;

    @GetMapping
    public List<Proprietario> retornaTodosProprietarios() {
        return proprietarioRepository.findAll();
    }

    @GetMapping("/{proprietarioId}")
    public ResponseEntity<Proprietario> retornaProprietarioPorId(@PathVariable Long proprietarioId) {
        Optional<Proprietario> proprietario = proprietarioRepository.findById(proprietarioId);

        return proprietario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Proprietario adicionaProprietario(@Valid @RequestBody Proprietario proprietario) {
        return proprietarioService.salvar(proprietario);
    }

    @PutMapping("/{proprietarioId}")
    public ResponseEntity<Proprietario> atualizar(@PathVariable Long proprietarioId, @Valid @RequestBody Proprietario proprietario) {

        if (!proprietarioRepository.existsById(proprietarioId)) {
            return ResponseEntity.notFound().build();
        }

        proprietario.setId(proprietarioId);

        Proprietario proprietarioAtualizado = proprietarioService.salvar(proprietario);

        return ResponseEntity.ok(proprietarioAtualizado);
    }

    @DeleteMapping("/{proprietarioId}")
    public ResponseEntity<Void> deletar(@PathVariable Long proprietarioId) {

        if (!proprietarioRepository.existsById(proprietarioId)) {
            ResponseEntity.notFound().build();
        }

        proprietarioService.excluir(proprietarioId);

        return ResponseEntity.noContent().build();
    }


}
