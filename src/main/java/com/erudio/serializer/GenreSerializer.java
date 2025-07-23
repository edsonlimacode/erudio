package com.erudio.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/*
* Esta classe ela serializa/retorna uma propriedade baseado em uma condição qualquer.
* */

public class GenreSerializer extends JsonSerializer<String> {
    @Override
    public void serialize(String value, JsonGenerator generator, SerializerProvider serializerProvider) throws IOException {

        String genreFormated = value.equals("Male") ? "M" : "F";

        generator.writeString(genreFormated);

    }
}
