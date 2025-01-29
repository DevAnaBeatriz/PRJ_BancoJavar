package com.database.ana.javer.test.controller;

import com.database.ana.javer.dto.ClienteDTO;
import com.database.ana.javer.exceptions.GlobalExceptionHandler;
import com.database.ana.javer.service.ClienteService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Import(GlobalExceptionHandler.class)
class ClienteControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ClienteService clienteService;

    @InjectMocks
    private com.database.ana.javer.controller.ClienteController clienteController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(clienteController).build();
    }

    @Test
    void listarClientes_DeveRetornarListaDeClientes() throws Exception {
        List<ClienteDTO> clientes = Arrays.asList(
                new ClienteDTO(1L, "Ana Beatriz", 11987654321L, true, 1000f, 100f)
        );

        when(clienteService.getAllClientes()).thenReturn(clientes);

        mockMvc.perform(get("/api/clientes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(clientes.size()));
    }

    @Test
    void criarCliente_ComDadosValidos_DeveRetornarClienteCriado() throws Exception {
        ClienteDTO clienteDTO = new ClienteDTO(null, "Ana Beatriz", 11987654321L, true, 1000f, null);
        ClienteDTO clienteCriado = new ClienteDTO(1L, "Ana Beatriz", 11987654321L, true, 1000f, 100f);

        when(clienteService.saveCliente(any(ClienteDTO.class))).thenReturn(clienteCriado);

        mockMvc.perform(post("/api/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\": \"Ana Beatriz\", \"telefone\": 11987654321, \"correntista\": true, \"saldoCc\": 1000.0 }"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(clienteCriado.getId()))
                .andExpect(jsonPath("$.nome").value(clienteCriado.getNome()));
    }

    @Test
    void criarCliente_ComDadosInvalidos_DeveRetornarErroValidacao() throws Exception {
        mockMvc.perform(post("/api/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\": \"Ana123\", \"telefone\": \"11987654\", \"correntista\": true, \"saldoCc\": -10.0 }"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void atualizarCliente_ComIdExistente_DeveAtualizarEVoltarCliente() throws Exception {
        ClienteDTO clienteDTO = new ClienteDTO(null, "Ana Beatriz", 11987654321L, true, 2000f, null);
        ClienteDTO clienteAtualizado = new ClienteDTO(1L, "Ana Beatriz", 11987654321L, true, 2000f, 200f);

        when(clienteService.updateCliente(eq(1L), any(ClienteDTO.class))).thenReturn(clienteAtualizado);

        mockMvc.perform(put("/api/clientes/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\": \"Ana Beatriz\", \"telefone\": 11987654321, \"correntista\": true, \"saldoCc\": 2000.0 }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.saldoCc").value(clienteAtualizado.getSaldoCc()));
    }


    @Test
    void deletarCliente_ComIdExistente_DeveRetornarNoContent() throws Exception {
        doNothing().when(clienteService).deleteCliente(1L);

        mockMvc.perform(delete("/api/clientes/1"))
                .andExpect(status().isNoContent());

        verify(clienteService, times(1)).deleteCliente(1L);
    }


}