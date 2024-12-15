package br.com.jpslg.transito.service;

import br.com.jpslg.transito.domain.exception.NegocioException;
import br.com.jpslg.transito.domain.model.Proprietario;
import br.com.jpslg.transito.domain.repository.ProprietarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class ProprietarioService {

    private ProprietarioRepository proprietarioRepository;

    public Proprietario buscarPorId(Long proprietarioId) {
        return proprietarioRepository.findById(proprietarioId)
                .orElseThrow(() -> new NegocioException("Proprietário não encontrado!"));
    }

    @Transactional
    public Proprietario salvar(Proprietario proprietario) {
        boolean emailExistente = proprietarioRepository.findByEmail(proprietario.getEmail())
                .filter(proprietarioSalvo -> !proprietarioSalvo.equals(proprietario))
                .isPresent();

        if (emailExistente) {
            throw new RuntimeException("Já existe um proprietario cadastrado com este email!");
        }

        return proprietarioRepository.save(proprietario);
    }

    @Transactional
    public void excluir(Long proprietarioId) {
        proprietarioRepository.deleteById(proprietarioId);
    }
}
