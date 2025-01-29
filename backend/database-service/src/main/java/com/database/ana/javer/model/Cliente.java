package com.database.ana.javer.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.Objects;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome não pode estar vazio")
    private String nome;

    private Long telefone;

    @NotNull(message = "Correntista não pode ser nulo")
    private Boolean correntista;

    @PositiveOrZero(message = "Saldo não pode ser negativo")
    private Float saldoCc;

    @Transient // esse campo não é persistido no banco de dados
    private Float scoreCredito;

    public Cliente() {}

    public Cliente(Long id, String nome, Long telefone, Boolean correntista, Float saldoCc) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.correntista = correntista;
        this.saldoCc = saldoCc;
    }

    // -------------------- os getters e setters!!! --------------------------
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public Long getTelefone() { return telefone; }
    public void setTelefone(Long telefone) { this.telefone = telefone; }

    public Boolean getCorrentista() { return correntista; }
    public void setCorrentista(Boolean correntista) { this.correntista = correntista; }

    public Float getSaldoCc() { return saldoCc; }
    public void setSaldoCc(Float saldoCc) {
        if (Boolean.FALSE.equals(this.correntista)) {
            this.saldoCc = 0.0f; // zera o saldo se não for correntista
        } else {
            this.saldoCc = saldoCc;
        }
    }

    public Float getScoreCredito() { return saldoCc != null ? saldoCc * 0.1f : 0f; }

    // -------------------- equals, hashCode e tostring --------------------------
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(id, cliente.id) &&
                Objects.equals(nome, cliente.nome) &&
                Objects.equals(telefone, cliente.telefone) &&
                Objects.equals(correntista, cliente.correntista) &&
                Objects.equals(saldoCc, cliente.saldoCc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, telefone, correntista, saldoCc);
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", telefone=" + telefone +
                ", correntista=" + correntista +
                ", saldoCc=" + saldoCc +
                ", scoreCredito=" + getScoreCredito() +
                '}';
    }
}
