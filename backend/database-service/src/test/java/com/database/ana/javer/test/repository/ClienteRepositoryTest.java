package com.database.ana.javer.test.repository;

import com.database.ana.javer.model.Cliente;
import com.database.ana.javer.repository.ClienteRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class ClienteRepositoryTest {

    @Autowired
    private ClienteRepository clienteRepository;

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        cliente = new Cliente();
        cliente.setNome("Ana Beatriz");
        cliente.setTelefone(11987654321L);
        cliente.setCorrentista(true);
        cliente.setSaldoCc(2000.0f);
    }

    @Test
    void salvarCliente_DevePersistirNoBanco() {
        Cliente clienteSalvo = clienteRepository.save(cliente);

        assertThat(clienteSalvo).isNotNull();
        assertThat(clienteSalvo.getId()).isNotNull();
        assertThat(clienteSalvo.getNome()).isEqualTo("Ana Beatriz");
    }

    @Test
    void buscarCliente_PorId_DeveRetornarCliente() {
        Cliente clienteSalvo = clienteRepository.save(cliente);
        Optional<Cliente> clienteEncontrado = clienteRepository.findById(clienteSalvo.getId());

        assertThat(clienteEncontrado).isPresent();
        assertThat(clienteEncontrado.get().getNome()).isEqualTo("Ana Beatriz");
    }

    @Test
    void buscarTodosClientes_DeveRetornarLista() {
        clienteRepository.save(cliente);
        List<Cliente> clientes = clienteRepository.findAll();

        assertThat(clientes).isNotEmpty();
        assertThat(clientes.size()).isGreaterThan(0);
    }

    @Test
    void atualizarCliente_DeveModificarDados() {
        Cliente clienteSalvo = clienteRepository.save(cliente);
        clienteSalvo.setNome("Beatriz Ana");

        Cliente clienteAtualizado = clienteRepository.save(clienteSalvo);

        assertThat(clienteAtualizado.getNome()).isEqualTo("Beatriz Ana");
    }

    @Test
    void deletarCliente_DeveRemoverDoBanco() {
        Cliente clienteSalvo = clienteRepository.save(cliente);
        clienteRepository.deleteById(clienteSalvo.getId());

        Optional<Cliente> clienteEncontrado = clienteRepository.findById(clienteSalvo.getId());
        assertThat(clienteEncontrado).isEmpty();
    }

    @Test
    void verificarSeClienteExistePorId() {
        Cliente clienteSalvo = clienteRepository.save(cliente);
        boolean existe = clienteRepository.existsById(clienteSalvo.getId());

        assertThat(existe).isTrue();
    }
}
