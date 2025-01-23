package br.com.jpslg.transito.common;

import br.com.jpslg.transito.api.dto.VeiculoDTO;
import br.com.jpslg.transito.domain.model.Veiculo;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        var modelMapper = new ModelMapper();

        //utiliza-se isso para quando os atributos da model soa diferentes do DTO
        modelMapper.createTypeMap(Veiculo.class, VeiculoDTO.class)
                .addMappings(mapper -> mapper.map(Veiculo::getPlaca, VeiculoDTO::setNumeroPlaca));

        return modelMapper;
    }

}
