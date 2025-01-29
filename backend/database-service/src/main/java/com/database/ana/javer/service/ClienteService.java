package com.database.ana.javer.service;

import com.database.ana.javer.dto.ClienteDTO;
import com.database.ana.javer.exceptions.ClienteNotFoundException;
import com.database.ana.javer.mapper.ClienteMapper;
import com.database.ana.javer.model.Cliente;
import com.database.ana.javer.repository.ClienteRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    private final ClienteRepository repository;
    private final ClienteMapper mapper;

    public ClienteService(ClienteRepository repository, ClienteMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public ClienteDTO saveCliente(ClienteDTO clienteDTO) {
        Cliente cliente = mapper.toEntity(clienteDTO);
        Cliente savedCliente = repository.save(cliente);
        return mapper.toDTO(savedCliente);
    }

    public List<ClienteDTO> getAllClientes() {
        return repository.findAll().stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    public ClienteDTO updateCliente(Long id, ClienteDTO clienteDTO) {
        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new ClienteNotFoundException("Cliente com ID " + id + " não encontrado"));
        cliente.setNome(clienteDTO.getNome());
        cliente.setTelefone(clienteDTO.getTelefone());
        cliente.setCorrentista(clienteDTO.getCorrentista());
        cliente.setSaldoCc(clienteDTO.getSaldoCc());
        return mapper.toDTO(repository.save(cliente));
    }

    public void deleteCliente(Long id) {
        if (!repository.existsById(id)) {
            throw new ClienteNotFoundException("Cliente com ID " + id + " não encontrado");
        }
        repository.deleteById(id);
    }
}