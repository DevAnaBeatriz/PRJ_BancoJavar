package com.client.ana.javer.service;

import com.client.ana.javer.dto.ClienteDTO;
import com.client.ana.javer.feign.ClienteFeignClient;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {

    @InjectMocks
    private ClienteService clienteService;

    @Mock
    private ClienteFeignClient clienteFeignClient;

    @Test
    void getAllClientes_DeveRetornarListaDeClientes() {
        List<ClienteDTO> clientesMock = List.of(new ClienteDTO(1L, "Ana", 11999999999L, true, 1000.0f));
        when(clienteFeignClient.getAllClientes()).thenReturn(clientesMock);

        List<ClienteDTO> resultado = clienteService.getAllClientes();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Ana", resultado.get(0).getNome());
    }

    @Test
    void createCliente_DeveRetornarClienteCriado() {
        ClienteDTO clienteNovo = new ClienteDTO(null, "Ana", 11999999999L, true, 1000.0f);
        ClienteDTO clienteCriado = new ClienteDTO(1L, "Ana", 11999999999L, true, 1000.0f);

        when(clienteFeignClient.createCliente(any(ClienteDTO.class))).thenReturn(clienteCriado);

        ClienteDTO resultado = clienteService.createCliente(clienteNovo);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Ana", resultado.getNome());
    }
}
