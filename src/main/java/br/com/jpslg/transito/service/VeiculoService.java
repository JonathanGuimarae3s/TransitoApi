package br.com.jpslg.transito.service;

import br.com.jpslg.transito.domain.enums.StatusVeiculo;
import br.com.jpslg.transito.domain.exception.NegocioException;
import br.com.jpslg.transito.domain.model.Proprietario;
import br.com.jpslg.transito.domain.model.Veiculo;
import br.com.jpslg.transito.domain.repository.VeiculoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;

@AllArgsConstructor
@Service
public class VeiculoService {

    private VeiculoRepository veiculoRepository;
    private final ProprietarioService proprietarioService;

    @Transactional
    public Veiculo cadastrar(Veiculo novoVeiculo) {

        boolean placaJaExiste = veiculoRepository.findByPlaca(novoVeiculo.getPlaca())
                .filter(veiculoSalvo -> !veiculoSalvo.equals(novoVeiculo))
                .isPresent();

        if (placaJaExiste) {
            throw new NegocioException("Já existe um veículo cadastrado com esta placa!");
        }

        Proprietario proprietario = proprietarioService.buscarPorId(novoVeiculo.getProprietario().getId());

        novoVeiculo.setProprietario(proprietario);
        novoVeiculo.setStatus(StatusVeiculo.REGULAR);
        novoVeiculo.setDataCadastro(LocalDateTime.now().atZone(ZoneId.systemDefault()).toOffsetDateTime());

        return veiculoRepository.save(novoVeiculo);
    }

}


