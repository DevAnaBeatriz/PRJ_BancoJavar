package com.database.ana.javer.dto;

import com.database.ana.javer.validations.CustomFloatDeserializerSaldo;
import com.database.ana.javer.validations.CustomLongDeserializerTelefone;
import com.database.ana.javer.validations.ValidCorrentista;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import jakarta.validation.constraints.*;

public class ClienteDTO {

    private Long id;

    @NotBlank(message = "O nome não pode estar vazio")

    @Pattern(regexp = "^[a-zA-ZÀ-ÿ\\s]+$", message = "O nome deve conter apenas letras e espaços")
    private String nome;

    @NotNull(message = "O telefone não pode ser nulo")
    @Positive(message = "O telefone deve conter apenas números positivos")
    @JsonDeserialize(using = CustomLongDeserializerTelefone.class)
    private Long telefone;

    @NotNull(message = "Correntista não pode ser nulo")
    @ValidCorrentista
    private Boolean correntista;

    @PositiveOrZero(message = "O saldo não pode ser negativo")
    @NotNull(message = "O saldo não pode ser nulo")
    @DecimalMin(value = "0.0", inclusive = true, message = "O saldo deve ser um número positivo ou zero")
    @JsonDeserialize(using = CustomFloatDeserializerSaldo.class)
    private Float saldoCc;

    private Float scoreCredito;

    public ClienteDTO() {}

    public ClienteDTO(Long id, String nome, Long telefone, Boolean correntista, Float saldoCc, Float scoreCredito) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.correntista = correntista;
        this.saldoCc = saldoCc;
        this.scoreCredito = scoreCredito;
    }

    // --------------------os getters e setters!!!--------------------------
    public Long getId() {return id;}
    public void setId(Long id) {
        if (this.id != null) {
            throw new UnsupportedOperationException("O ID do cliente não pode ser alterado.");
        }this.id = id;}

    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}

    public Long getTelefone() {return telefone;}
    public void setTelefone(Long telefone) {this.telefone = telefone;}

    public Boolean getCorrentista() {return correntista;}
    public void setCorrentista(Boolean correntista) {this.correntista = correntista;}

    public Float getSaldoCc() {return saldoCc;}
    public void setSaldoCc(Float saldoCc) {this.saldoCc = saldoCc;}

    public Float getScoreCredito() {return scoreCredito;}
}
