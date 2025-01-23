package br.com.jpslg.transito.api.dto;

import br.com.jpslg.transito.domain.validationGroups.ValidationGroups;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProprietarioDTO {

    private Long id;

    private String nome;
}
