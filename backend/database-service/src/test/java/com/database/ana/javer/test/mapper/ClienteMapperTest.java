package com.database.ana.javer.test.mapper;

import com.database.ana.javer.dto.ClienteDTO;
import com.database.ana.javer.mapper.ClienteMapper;
import com.database.ana.javer.model.Cliente;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClienteMapperTest {

    private final ClienteMapper clienteMapper = new ClienteMapper();

    @Test
    void toDTO_DeveConverterClienteParaClienteDTO() {
        Cliente cliente = new Cliente(1L, "Ana Beatriz", 11987654321L, true, 2000.0f);

        ClienteDTO dto = clienteMapper.toDTO(cliente);

        assertNotNull(dto);
        assertEquals(1L, dto.getId());
        assertEquals("Ana Beatriz", dto.getNome());
        assertEquals(11987654321L, dto.getTelefone());
        assertEquals(true, dto.getCorrentista());
        assertEquals(2000.0f, dto.getSaldoCc());
        assertEquals(200.0f, dto.getScoreCredito());
    }

    @Test
    void toEntity_DeveConverterClienteDTOParaCliente() {
        ClienteDTO dto = new ClienteDTO(1L, "Ana Beatriz", 11987654321L, true, 750.0f, null);

        Cliente cliente = clienteMapper.toEntity(dto);

        assertNotNull(cliente);
        assertEquals(1L, cliente.getId());
        assertEquals("Ana Beatriz", cliente.getNome());
        assertEquals(11987654321L, cliente.getTelefone());
        assertEquals(true, cliente.getCorrentista());
        assertEquals(750.0f, cliente.getSaldoCc());
        assertEquals(75.0f, cliente.getScoreCredito());
    }

    @Test
    void toDTO_ComClienteNulo_DeveRetornarNulo() {
        ClienteDTO dto = clienteMapper.toDTO(null);
        assertNull(dto);
    }

    @Test
    void toEntity_ComClienteDTONulo_DeveRetornarNulo() {
        Cliente cliente = clienteMapper.toEntity(null);
        assertNull(cliente);
    }
}
