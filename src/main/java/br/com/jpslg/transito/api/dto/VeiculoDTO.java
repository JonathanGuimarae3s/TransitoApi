package br.com.jpslg.transito.api.dto;

import br.com.jpslg.transito.domain.enums.StatusVeiculo;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class VeiculoDTO {
    private Long id;

    private ProprietarioDTO proprietario;

    private String marca;
    private String numeroPlaca;
    private String modelo;

    private OffsetDateTime dataCadastro;
    private OffsetDateTime dataApreensao;

    private StatusVeiculo status;


}
