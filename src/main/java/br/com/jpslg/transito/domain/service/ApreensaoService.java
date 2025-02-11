package br.com.jpslg.transito.domain.service;

import br.com.jpslg.transito.domain.enums.StatusVeiculo;
import br.com.jpslg.transito.domain.model.Veiculo;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;

@AllArgsConstructor
@RestController
public class ApreensaoService {

    private final VeiculoService veiculoService;

    @Transactional
    public void apreender(Long veiculoId) {
        Veiculo veiculo = veiculoService.buscar(veiculoId);
        veiculo.apreender();
    }

    @Transactional
    public void liberar(Long veiculoId) {
        Veiculo veiculo = veiculoService.buscar(veiculoId);
        veiculo.liberar();
    }
}
