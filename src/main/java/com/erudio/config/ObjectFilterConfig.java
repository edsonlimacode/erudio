package com.erudio.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//Filtra proriedades, podendo remover nulas, vazia e etc
@Configuration
public class ObjectFilterConfig {

    @Bean
    public ObjectMapper mapper() {

        var mapper = new ObjectMapper();

        var filters = new SimpleFilterProvider()
                .addFilter("PersonFilterProperties",
                        SimpleBeanPropertyFilter.serializeAllExcept("propertyPassword")
                ); //posso adicionar mais filtros se quiser.

        mapper.setFilterProvider(filters);

        return mapper;
    }

}
