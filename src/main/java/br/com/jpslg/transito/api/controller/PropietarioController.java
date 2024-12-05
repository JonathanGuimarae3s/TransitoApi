package br.com.jpslg.transito.api.controller;

import br.com.jpslg.transito.domain.model.Propietario;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PropietarioController {

    @GetMapping("/propietarios")
    public List<Propietario> testar() {
        List<Propietario> propietarioList = new ArrayList<>();

        propietarioList.add(new Propietario(1L, "Jonathan", "jj@gmail.com", "21221"));
        propietarioList.add(new Propietario(2L, "Maria Silva", "maria@gmail.com", "12345"));
        propietarioList.add(new Propietario(3L, "Carlos Souza", "carlos@gmail.com", "67890"));
        propietarioList.add(new Propietario(4L, "Ana Lima", "ana@gmail.com", "11223"));
        propietarioList.add(new Propietario(5L, "Pedro Rocha", "pedro@gmail.com", "33445"));
        propietarioList.add(new Propietario(6L, "João Oliveira", "joao@gmail.com", "55667"));

        return propietarioList;
    }

}
