package br.com.jpslg.transito.domain.repository;

import br.com.jpslg.transito.domain.model.Proprietario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProprietarioRepository extends JpaRepository<Proprietario, Long> {


    List<Proprietario> findByNomeContaining(String nome);

}
