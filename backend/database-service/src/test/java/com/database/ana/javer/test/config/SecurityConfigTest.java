package com.database.ana.javer.test.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Base64;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class SecurityConfigTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private String basicAuthHeader;

    @BeforeEach
    void setUp() {
        String username = "admin";// gerando o header de autenticação para o usuário admin
        String password = "ana123";
        String encodedCredentials = Base64.getEncoder().encodeToString((username + ":" + password).getBytes());
        basicAuthHeader = "Basic " + encodedCredentials;
    }

    @Test
    void testAccessProtectedEndpointWithoutAuth_shouldReturn401() throws Exception {
        mockMvc.perform(get("/api/clientes"))
                .andExpect(status().isUnauthorized()); // 401 nao autorizado
    }

    @Test
    void testAccessProtectedEndpointWithAuth_shouldReturn200() throws Exception {
        mockMvc.perform(get("/api/clientes")
                        .header(HttpHeaders.AUTHORIZATION, basicAuthHeader))
                .andExpect(status().isOk()) // 200
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testCreateCliente_withAuth_shouldReturn201() throws Exception {
        String newClienteJson = """
                {
                    "id": null,
                    "nome": "João Silva",
                    "telefone": 11987654321,
                    "correntista": true,
                    "saldoCc": 1000.0,
                    "scoreCredito": null
                }
                """;

        mockMvc.perform(post("/api/clientes")
                        .header(HttpHeaders.AUTHORIZATION, basicAuthHeader)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newClienteJson))
                .andExpect(status().isCreated()) // 201
                .andExpect(jsonPath("$.nome").value("João Silva"))
                .andExpect(jsonPath("$.telefone").value(11987654321L))
                .andExpect(jsonPath("$.correntista").value(true));
    }

    @Test
    void testDeleteCliente_withAuth_shouldReturn204() throws Exception {
        mockMvc.perform(delete("/api/clientes/1") // assumindo que há um cliente com ID 1 no banco de dados
                        .header(HttpHeaders.AUTHORIZATION, basicAuthHeader))
                .andExpect(status().isNoContent()); //204
    }
}
