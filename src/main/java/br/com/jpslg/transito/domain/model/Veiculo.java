package br.com.jpslg.transito.domain.model;

import br.com.jpslg.transito.domain.enums.StatusVeiculo;
import br.com.jpslg.transito.domain.exception.NegocioException;
import br.com.jpslg.transito.domain.validationGroups.ValidationGroups;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.validation.groups.ConvertGroup;
import jakarta.validation.groups.Default;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.internal.bytebuddy.agent.builder.AgentBuilder;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Valid
    @ConvertGroup(from = Default.class, to = ValidationGroups.ProprietarioId.class)
    @NotNull
    @ManyToOne
    private Proprietario proprietario;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Enumerated(EnumType.STRING)
    private StatusVeiculo status;

    @NotBlank
    private String marca;

    @NotBlank
    @Size(max = 20)
    @Pattern(regexp = "[A-Z]{3}[0-9][0-9A-Z][0-9]{2}")
    private String placa;

    @NotBlank
    @Size(max = 20)
    private String modelo;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private OffsetDateTime dataCadastro;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private OffsetDateTime dataApreensao;

    @OneToMany(mappedBy = "veiculo", cascade = CascadeType.PERSIST)
    private List<Autuacao> autuacaoList = new ArrayList<>();

    public Autuacao adicionarAutuacao(Autuacao autuacao) {
        autuacao.setDataOcorrencia(OffsetDateTime.now());
        autuacao.setVeiculo(this);

        getAutuacaoList().add(autuacao);

        return autuacao;
    }

    public void apreender() {

        if (estaApreendido()) {
            throw new NegocioException("Veículo já se encontra apreendido!");
        }

        setStatus(StatusVeiculo.APREENDIDO);
        setDataApreensao(OffsetDateTime.now());
    }

    public void liberar() {

        if (!estaApreendido()) {
            throw new NegocioException("Veículo já está liberado!");
        }

        setStatus(StatusVeiculo.REGULAR);
        setDataApreensao(null);
    }

    private boolean estaApreendido() {
        return StatusVeiculo.APREENDIDO.equals(getStatus());
    }

}
