package com.database.ana.javer.mapper;

import com.database.ana.javer.dto.ClienteDTO;
import com.database.ana.javer.model.Cliente;

import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {

    public ClienteDTO toDTO(Cliente cliente) {
        if (cliente == null) {
            return null;
        }
        return new ClienteDTO(
                cliente.getId(),
                cliente.getNome(),
                cliente.getTelefone(),
                cliente.getCorrentista(),
                cliente.getSaldoCc(),
                cliente.getScoreCredito()
        );
    }

    public Cliente toEntity(ClienteDTO clienteDTO) {
        if (clienteDTO == null) {
            return null;
        }
        Cliente cliente = new Cliente();
        cliente.setId(clienteDTO.getId());
        cliente.setNome(clienteDTO.getNome());
        cliente.setTelefone(clienteDTO.getTelefone());
        cliente.setCorrentista(clienteDTO.getCorrentista());
        cliente.setSaldoCc(clienteDTO.getSaldoCc());
        return cliente;
    }
}
