package br.com.jpslg.transito.api.input;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProprietarioIdInput {

    @NotNull
    private Long id;
}
