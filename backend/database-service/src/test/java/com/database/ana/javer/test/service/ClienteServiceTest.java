package com.database.ana.javer.test.service;

import com.database.ana.javer.dto.ClienteDTO;
import com.database.ana.javer.exceptions.ClienteNotFoundException;
import com.database.ana.javer.mapper.ClienteMapper;
import com.database.ana.javer.model.Cliente;
import com.database.ana.javer.repository.ClienteRepository;
import com.database.ana.javer.service.ClienteService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private ClienteMapper clienteMapper;

    @InjectMocks
    private ClienteService clienteService;

    private Cliente cliente;
    private ClienteDTO clienteDTO;

    @BeforeEach
    void setUp() {
        cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("Ana Beatriz");
        cliente.setTelefone(11987654321L);
        cliente.setCorrentista(true);
        cliente.setSaldoCc(2000.0f);

        clienteDTO = new ClienteDTO();
        clienteDTO.setId(1L);
        clienteDTO.setNome("Ana Beatriz");
        clienteDTO.setTelefone(11987654321L);
        clienteDTO.setCorrentista(true);
        clienteDTO.setSaldoCc(2000.0f);
    }

    @Test
    void salvarCliente_DeveRetornarClienteDTO() {
        when(clienteMapper.toEntity(clienteDTO)).thenReturn(cliente);
        when(clienteRepository.save(cliente)).thenReturn(cliente);
        when(clienteMapper.toDTO(cliente)).thenReturn(clienteDTO);

        ClienteDTO resultado = clienteService.saveCliente(clienteDTO);

        assertThat(resultado).isNotNull();
        assertThat(resultado.getNome()).isEqualTo("Ana Beatriz");

        verify(clienteRepository, times(1)).save(cliente);
    }

    @Test
    void listarTodosClientes_DeveRetornarListaClientesDTO() {
        when(clienteRepository.findAll()).thenReturn(List.of(cliente));
        when(clienteMapper.toDTO(cliente)).thenReturn(clienteDTO);

        List<ClienteDTO> resultado = clienteService.getAllClientes();

        assertThat(resultado).isNotEmpty();
        assertThat(resultado.size()).isEqualTo(1);
        assertThat(resultado.get(0).getNome()).isEqualTo("Ana Beatriz");

        verify(clienteRepository, times(1)).findAll();
    }

    @Test
    void atualizarCliente_ComIdExistente_DeveRetornarClienteDTOAtualizado() {
        ClienteDTO clienteAtualizadoDTO = new ClienteDTO();
        clienteAtualizadoDTO.setId(1L);
        clienteAtualizadoDTO.setNome("Beatriz Ana");
        clienteAtualizadoDTO.setTelefone(11987654321L);
        clienteAtualizadoDTO.setCorrentista(true);
        clienteAtualizadoDTO.setSaldoCc(2500.0f);

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(clienteRepository.save(cliente)).thenReturn(cliente);
        when(clienteMapper.toDTO(cliente)).thenReturn(clienteAtualizadoDTO);

        ClienteDTO resultado = clienteService.updateCliente(1L, clienteAtualizadoDTO);

        assertThat(resultado.getNome()).isEqualTo("Beatriz Ana");
        assertThat(resultado.getSaldoCc()).isEqualTo(2500.0f);

        verify(clienteRepository, times(1)).findById(1L);
        verify(clienteRepository, times(1)).save(cliente);
    }

    @Test
    void atualizarCliente_ComIdInexistente_DeveLancarExcecao() {
        when(clienteRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> clienteService.updateCliente(99L, clienteDTO))
                .isInstanceOf(ClienteNotFoundException.class)
                .hasMessageContaining("Cliente com ID 99 não encontrado");

        verify(clienteRepository, times(1)).findById(99L);
        verify(clienteRepository, never()).save(any());
    }

    @Test
    void deletarCliente_ComIdExistente_DeveRemoverCliente() {
        when(clienteRepository.existsById(1L)).thenReturn(true);
        doNothing().when(clienteRepository).deleteById(1L);

        clienteService.deleteCliente(1L);

        verify(clienteRepository, times(1)).existsById(1L);
        verify(clienteRepository, times(1)).deleteById(1L);
    }

    @Test
    void deletarCliente_ComIdInexistente_DeveLancarExcecao() {
        when(clienteRepository.existsById(99L)).thenReturn(false);

        assertThatThrownBy(() -> clienteService.deleteCliente(99L))
                .isInstanceOf(ClienteNotFoundException.class)
                .hasMessageContaining("Cliente com ID 99 não encontrado");

        verify(clienteRepository, times(1)).existsById(99L);
        verify(clienteRepository, never()).deleteById(anyLong());
    }
}
