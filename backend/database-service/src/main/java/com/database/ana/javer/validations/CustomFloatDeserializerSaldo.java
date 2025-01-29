package com.database.ana.javer.validations;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class CustomFloatDeserializerSaldo extends JsonDeserializer<Float> {

    @Override
    public Float deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        try {
            return Float.valueOf(p.getText());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("O campo saldo deve conter apenas números válidos e não pode estar vazio. Por favor, preencha novamente!!!");
        }
    }
}
