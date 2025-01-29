package com.client.ana.javer.controller;

import com.client.ana.javer.dto.ClienteDTO;
import com.client.ana.javer.service.ClienteService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ClienteControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ClienteService clienteService;

    @InjectMocks
    private ClienteController clienteController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(clienteController).build();
    }

    @Test
    void listarClientes_DeveRetornarListaDeClientes() throws Exception {
        List<ClienteDTO> clientes = Arrays.asList(
                new ClienteDTO(1L, "Ana Beatriz", 11999999999L, true, 1000.0f)
        );

        when(clienteService.getAllClientes()).thenReturn(clientes);

        mockMvc.perform(get("/api/clientes")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(clientes.size()))
                .andExpect(jsonPath("$[0].nome").value("Ana Beatriz"))
                .andExpect(jsonPath("$[0].saldoCc").value(1000.0))
                .andExpect(jsonPath("$[0].scoreCredito").value(100.0));

        verify(clienteService, times(1)).getAllClientes();
    }

    @Test
    void criarCliente_DeveRetornarClienteCriado() throws Exception {
        ClienteDTO clienteDTO = new ClienteDTO(1L, "Ana Beatriz", 11999999999L, true, 1000.0f);

        when(clienteService.createCliente(any(ClienteDTO.class))).thenReturn(clienteDTO);

        String jsonBody = """
            {
                "id": 1,
                "nome": "Ana Beatriz",
                "telefone": 11999999999,
                "correntista": true,
                "saldoCc": 1000.0
            }
            """;

        mockMvc.perform(post("/api/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("Ana Beatriz"))
                .andExpect(jsonPath("$.saldoCc").value(1000.0))
                .andExpect(jsonPath("$.scoreCredito").value(100.0));

        verify(clienteService, times(1)).createCliente(any(ClienteDTO.class));
    }


    @Test
    void atualizarCliente_DeveRetornarClienteAtualizado() throws Exception {
        ClienteDTO clienteDTO = new ClienteDTO(1L, "Ana Beatriz", 11999999999L, true, 2000.0f);

        when(clienteService.updateCliente(eq(1L), any(ClienteDTO.class))).thenReturn(clienteDTO);

        String jsonBody = """
                {
                    "id": 1,
                    "nome": "Ana Beatriz",
                    "telefone": 11999999999,
                    "correntista": true,
                    "saldoCc": 2000.0
                }
                """;

        mockMvc.perform(put("/api/clientes/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.saldoCc").value(2000.0))
                .andExpect(jsonPath("$.scoreCredito").value(200.0));

        verify(clienteService, times(1)).updateCliente(eq(1L), any(ClienteDTO.class));
    }

    @Test
    void deletarCliente_DeveRetornarNoContent() throws Exception {
        doNothing().when(clienteService).deleteCliente(1L);

        mockMvc.perform(delete("/api/clientes/1"))
                .andExpect(status().isNoContent());

        verify(clienteService, times(1)).deleteCliente(1L);
    }

    @Test
    void criarCliente_NaoCorrentista_DeveTerSaldoZero() throws Exception {
        ClienteDTO clienteDTO = new ClienteDTO(2L, "Carlos Silva", 11888888888L, false, 1000.0f);

        when(clienteService.createCliente(any(ClienteDTO.class))).thenReturn(clienteDTO);

        String jsonBody = """
                {
                    "id": 2,
                    "nome": "Carlos Silva",
                    "telefone": 11888888888,
                    "correntista": false,
                    "saldoCc": 1000.0
                }
                """;

        mockMvc.perform(post("/api/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("Carlos Silva"))
                .andExpect(jsonPath("$.saldoCc").value(0.0)) // deve ser 0 pq não é correntista
                .andExpect(jsonPath("$.scoreCredito").value(0.0));

        verify(clienteService, times(1)).createCliente(any(ClienteDTO.class));
    }
}
