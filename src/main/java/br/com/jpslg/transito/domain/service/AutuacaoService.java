package br.com.jpslg.transito.domain.service;

import br.com.jpslg.transito.domain.model.Autuacao;
import br.com.jpslg.transito.domain.model.Veiculo;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AutuacaoService {


    private final VeiculoService veiculoService;

    @Transactional
    public Autuacao registrar(Long veiculoId, Autuacao novaAutuacao) {
        Veiculo veiculo = veiculoService.buscar(veiculoId);


        return veiculo.adicionarAutuacao(novaAutuacao);
    }

}
