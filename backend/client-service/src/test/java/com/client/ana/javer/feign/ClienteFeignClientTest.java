package com.client.ana.javer.feign;

import com.client.ana.javer.dto.ClienteDTO;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClienteFeignClientTest {

    @Mock
    private ClienteFeignClient clienteFeignClient;

    @Test
    void deveRetornarListaDeClientes() {
        ClienteDTO cliente1 = new ClienteDTO(1L, "Ana Beatriz", 11999999999L, true, 1000.0f);
        ClienteDTO cliente2 = new ClienteDTO(2L, "João Silva", 11888888888L, false, 0.0f);
        List<ClienteDTO> clientesMock = Arrays.asList(cliente1, cliente2);

        when(clienteFeignClient.getAllClientes()).thenReturn(clientesMock);

        List<ClienteDTO> resultado = clienteFeignClient.getAllClientes();

        assertEquals(2, resultado.size());
        assertEquals("Ana Beatriz", resultado.get(0).getNome());
        assertEquals("João Silva", resultado.get(1).getNome());

        verify(clienteFeignClient, times(1)).getAllClientes();
    }

    @Test
    void deveCriarCliente() {
        ClienteDTO novoCliente = new ClienteDTO(null, "Carlos Souza", 11977777777L, true, 500.0f);
        ClienteDTO clienteCriado = new ClienteDTO(3L, "Carlos Souza", 11977777777L, true, 500.0f);

        when(clienteFeignClient.createCliente(novoCliente)).thenReturn(clienteCriado);

        ClienteDTO resultado = clienteFeignClient.createCliente(novoCliente);

        assertEquals(3L, resultado.getId());
        assertEquals("Carlos Souza", resultado.getNome());
        assertEquals(11977777777L, resultado.getTelefone());

        verify(clienteFeignClient, times(1)).createCliente(novoCliente);
    }

    @Test
    void deveAtualizarCliente() {
        ClienteDTO clienteAtualizado = new ClienteDTO(1L, "Ana Beatriz Silva", 11999999999L, true, 1500.0f);

        when(clienteFeignClient.updateCliente(1L, clienteAtualizado)).thenReturn(clienteAtualizado);

        ClienteDTO resultado = clienteFeignClient.updateCliente(1L, clienteAtualizado);

        assertEquals("Ana Beatriz Silva", resultado.getNome());
        assertEquals(1500.0f, resultado.getSaldoCc());

        verify(clienteFeignClient, times(1)).updateCliente(1L, clienteAtualizado);
    }

    @Test
    void deveDeletarCliente() {
        doNothing().when(clienteFeignClient).deleteCliente(1L);

        clienteFeignClient.deleteCliente(1L);

        verify(clienteFeignClient, times(1)).deleteCliente(1L);
    }
}
