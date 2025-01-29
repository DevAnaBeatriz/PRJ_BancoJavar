package com.client.ana.javer.exceptions;

import feign.Response;
import feign.codec.ErrorDecoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.util.StreamUtils;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class FeignErrorDecoder implements ErrorDecoder {

    private static final Logger logger = LoggerFactory.getLogger(FeignErrorDecoder.class);

    @Override
    public Exception decode(String methodKey, Response response) {
        HttpStatus status = HttpStatus.valueOf(response.status());

        String errorMessage = "Vixi! Erro desconhecido";
        try {
            if (response.body() != null) {
                errorMessage = StreamUtils.copyToString(response.body().asInputStream(), StandardCharsets.UTF_8);
            }
        } catch (IOException ex) {
            logger.error("Ops! Erro ao ler o corpo da resposta Feign: {}", ex.getMessage());
        }

        logger.error("Eita! Erro na comunicação Feign: [{}] - {}", status, errorMessage);

        return new ResponseStatusException(status, errorMessage);
    }
}
