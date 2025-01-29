package com.database.ana.javer.test.model;

import com.database.ana.javer.model.Cliente;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {

    @Test
    void testConstrutor_ComDadosValidos_DeveCriarCliente() {
        Cliente cliente = new Cliente(1L, "Ana Beatriz", 11987654321L, true, 1000.0f);

        assertNotNull(cliente);
        assertEquals(1L, cliente.getId());
        assertEquals("Ana Beatriz", cliente.getNome());
        assertEquals(11987654321L, cliente.getTelefone());
        assertTrue(cliente.getCorrentista());
        assertEquals(1000.0f, cliente.getSaldoCc());
        assertEquals(100.0f, cliente.getScoreCredito());
    }

    @Test
    void testSettersEGetters() {
        Cliente cliente = new Cliente();
        cliente.setId(2L);
        cliente.setNome("João Silva");
        cliente.setTelefone(11999887766L);
        cliente.setCorrentista(true);
        cliente.setSaldoCc(2000.0f);

        assertEquals(2L, cliente.getId());
        assertEquals("João Silva", cliente.getNome());
        assertEquals(11999887766L, cliente.getTelefone());
        assertTrue(cliente.getCorrentista());
        assertEquals(2000.0f, cliente.getSaldoCc());
        assertEquals(200.0f, cliente.getScoreCredito());
    }

    @Test
    void testSettersEGetters_NaoCorrentista() {
        Cliente cliente = new Cliente();
        cliente.setId(2L);
        cliente.setNome("João Silva");
        cliente.setTelefone(11999887766L);
        cliente.setCorrentista(false);
        cliente.setSaldoCc(2000.0f);

        assertEquals(2L, cliente.getId());
        assertEquals("João Silva", cliente.getNome());
        assertEquals(11999887766L, cliente.getTelefone());
        assertFalse(cliente.getCorrentista());
        assertEquals(0.0f, cliente.getSaldoCc());
        assertEquals(0.0f, cliente.getScoreCredito());
    }

    @Test
    void testScoreCredito_CalculoCorreto() {
        Cliente cliente = new Cliente(3L, "Maria Oliveira", 11955554433L, true, 1500.0f);

        assertEquals(150.0f, cliente.getScoreCredito());// verificando o cálculo do scoreCredito com saldo válido

        cliente.setSaldoCc(0.0f);
        assertEquals(0.0f, cliente.getScoreCredito());

        cliente.setSaldoCc(null); // caso o saldo seja nulo
        assertEquals(0.0f, cliente.getScoreCredito());
    }

    @Test
    void testEquals_HashCode() {
        Cliente cliente1 = new Cliente(4L, "Carlos Souza", 11911112222L, true, 500.0f);
        Cliente cliente2 = new Cliente(4L, "Carlos Souza", 11911112222L, true, 500.0f);

        assertEquals(cliente1, cliente2); // teste de igualdade
        assertEquals(cliente1.hashCode(), cliente2.hashCode());
    }

    @Test
    void testToString() {
        Cliente cliente = new Cliente(5L, "Fernanda Lima", 11922223333L, false, 1200.0f);

        String esperado = "Cliente{id=5, nome='Fernanda Lima', telefone=11922223333, correntista=false, saldoCc=1200.0, scoreCredito=120.0}";
        assertEquals(esperado, cliente.toString());
    }

    @Test
    void testConstrutorSemParametros_DeveCriarClienteVazio() {
        Cliente cliente = new Cliente();

        assertNull(cliente.getId());
        assertNull(cliente.getNome());
        assertNull(cliente.getTelefone());
        assertNull(cliente.getCorrentista());
        assertNull(cliente.getSaldoCc());
        assertEquals(0.0f, cliente.getScoreCredito());
    }
}
