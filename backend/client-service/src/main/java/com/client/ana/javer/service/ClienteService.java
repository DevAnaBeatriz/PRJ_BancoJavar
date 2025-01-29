package com.client.ana.javer.service;

import com.client.ana.javer.dto.ClienteDTO;
import com.client.ana.javer.feign.ClienteFeignClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ClienteService {

    private static final Logger logger = LoggerFactory.getLogger(ClienteService.class);

    @Autowired
    private ClienteFeignClient clienteFeignClient;

    public List<ClienteDTO> getAllClientes() {
        try {
            return clienteFeignClient.getAllClientes();
        } catch (Exception ex) {
            logger.error("Erro ao buscar clientes: {}", ex.getMessage());
            throw ex;
        }
    }

    public ClienteDTO createCliente(ClienteDTO clienteDTO) {
        try {
            return clienteFeignClient.createCliente(clienteDTO);
        } catch (ResponseStatusException ex) {
            logger.error("Erro ao criar cliente: {}", ex.getReason());
            throw ex;
        }
    }

    public ClienteDTO updateCliente(Long id, ClienteDTO clienteDTO) {
        try {
            return clienteFeignClient.updateCliente(id, clienteDTO);
        } catch (Exception ex) {
            logger.error("Erro ao atualizar cliente: {}", ex.getMessage());
            throw ex;
        }
    }

    public void deleteCliente(Long id) {
        try {
            clienteFeignClient.deleteCliente(id);
        } catch (Exception ex) {
            logger.error("Erro ao deletar cliente: {}", ex.getMessage());
            throw ex;
        }
    }
}