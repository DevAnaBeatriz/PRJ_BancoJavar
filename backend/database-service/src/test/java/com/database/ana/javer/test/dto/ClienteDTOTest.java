package com.database.ana.javer.test.dto;

import com.database.ana.javer.dto.ClienteDTO;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class ClienteDTOTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void validarClienteDTO_ComDadosValidos_NaoDeveGerarViolacoes() {
        ClienteDTO clienteDTO = new ClienteDTO(1L, "Ana Beatriz", 11987654321L, true, 2000.0f, 750.0f);

        Set<ConstraintViolation<ClienteDTO>> violacoes = validator.validate(clienteDTO);

        assertThat(violacoes).isEmpty();
    }

    @Test
    void validarClienteDTO_ComNomeVazio_DeveGerarViolacao() {
        ClienteDTO clienteDTO = new ClienteDTO(1L, "", 11987654321L, true, 2000.0f, 750.0f);

        Set<ConstraintViolation<ClienteDTO>> violacoes = validator.validate(clienteDTO);

        assertThat(violacoes).isNotEmpty();
        assertThat(violacoes).anyMatch(v -> v.getMessage().equals("O nome não pode estar vazio"));
    }

    @Test
    void validarClienteDTO_ComNomeInvalido_DeveGerarViolacao() {
        ClienteDTO clienteDTO = new ClienteDTO(1L, "Ana123", 11987654321L, true, 2000.0f, 750.0f);

        Set<ConstraintViolation<ClienteDTO>> violacoes = validator.validate(clienteDTO);

        assertThat(violacoes).isNotEmpty();
        assertThat(violacoes).anyMatch(v -> v.getMessage().equals("O nome deve conter apenas letras e espaços"));
    }

    @Test
    void validarClienteDTO_ComTelefoneNegativo_DeveGerarViolacao() {
        ClienteDTO clienteDTO = new ClienteDTO(1L, "Ana Beatriz", -11987654321L, true, 2000.0f, 750.0f);

        Set<ConstraintViolation<ClienteDTO>> violacoes = validator.validate(clienteDTO);

        assertThat(violacoes).isNotEmpty();
        assertThat(violacoes).anyMatch(v -> v.getMessage().equals("O telefone deve conter apenas números positivos"));
    }

    @Test
    void validarClienteDTO_ComTelefoneNulo_DeveGerarViolacao() {
        ClienteDTO clienteDTO = new ClienteDTO(1L, "Ana Beatriz", null, true, 2000.0f, 750.0f);

        Set<ConstraintViolation<ClienteDTO>> violacoes = validator.validate(clienteDTO);

        assertThat(violacoes).isNotEmpty();
        assertThat(violacoes).anyMatch(v -> v.getMessage().equals("O telefone não pode ser nulo"));
    }

    @Test
    void validarClienteDTO_ComCorrentistaNulo_DeveGerarViolacao() {
        ClienteDTO clienteDTO = new ClienteDTO(1L, "Ana Beatriz", 11987654321L, null, 2000.0f, 750.0f);

        Set<ConstraintViolation<ClienteDTO>> violacoes = validator.validate(clienteDTO);

        assertThat(violacoes).isNotEmpty();
        assertThat(violacoes).anyMatch(v -> v.getMessage().equals("Correntista não pode ser nulo"));
    }

    @Test
    void validarClienteDTO_ComSaldoNegativo_DeveGerarViolacao() {
        ClienteDTO clienteDTO = new ClienteDTO(1L, "Ana Beatriz", 11987654321L, true, -2000.0f, 750.0f);

        Set<ConstraintViolation<ClienteDTO>> violacoes = validator.validate(clienteDTO);

        assertThat(violacoes).isNotEmpty();
        assertThat(violacoes).anyMatch(v -> v.getMessage().equals("O saldo não pode ser negativo"));
    }

    @Test
    void validarClienteDTO_ComSaldoNulo_DeveGerarViolacao() {
        ClienteDTO clienteDTO = new ClienteDTO(1L, "Ana Beatriz", 11987654321L, true, null, 750.0f);

        Set<ConstraintViolation<ClienteDTO>> violacoes = validator.validate(clienteDTO);

        assertThat(violacoes).isNotEmpty();
        assertThat(violacoes).anyMatch(v -> v.getMessage().equals("O saldo não pode ser nulo"));
    }
}
