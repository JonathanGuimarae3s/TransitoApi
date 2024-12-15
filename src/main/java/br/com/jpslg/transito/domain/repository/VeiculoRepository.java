package br.com.jpslg.transito.domain.repository;

import br.com.jpslg.transito.domain.model.Proprietario;
import br.com.jpslg.transito.domain.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

    Optional<Veiculo> findByPlaca(String placa);


}