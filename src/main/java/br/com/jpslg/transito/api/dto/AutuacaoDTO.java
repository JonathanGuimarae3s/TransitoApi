package br.com.jpslg.transito.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class AutuacaoDTO {
    private Long id;
    private String descricao;
    private BigDecimal valorMulta;
    private OffsetDateTime dataOcorrencia;
}
