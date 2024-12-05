package br.com.jpslg.transito.api.controller;

import br.com.jpslg.transito.domain.model.Proprietario;
import br.com.jpslg.transito.domain.repository.ProprietarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
public class ProprietarioController {

    private ProprietarioRepository proprietarioRepository;

    @GetMapping("/proprietarios")
    public List<Proprietario> retornaTodosProprietarios() {
        return proprietarioRepository.findAll();
    }

}
