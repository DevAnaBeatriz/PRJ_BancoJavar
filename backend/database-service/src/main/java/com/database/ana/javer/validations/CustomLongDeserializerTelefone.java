package com.database.ana.javer.validations;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class CustomLongDeserializerTelefone extends JsonDeserializer<Long> {

    @Override
    public Long deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String telefone = p.getText();

        try {
            if (!telefone.matches("\\d+")) { // verificando se o telefone contém apenas números mesmo
                throw new IllegalArgumentException("O campo telefone deve conter apenas números válidos.");
            }

            if (telefone.length() < 10 || telefone.length() > 11) { // verificando se o tamanho do telefone está entre 10  (1120202020) ou 11 (11920202020)
                throw new IllegalArgumentException("O campo telefone deve ter entre 10 e 11 dígitos.");
            }

            return Long.valueOf(telefone);

        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("O campo telefone deve conter apenas números válidos e não pode estar nulo. Por obséquio, preencha novamente!");
        }
    }
}

