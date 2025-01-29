package com.client.ana.javer.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Schema(description = "Representação de um Cliente")
public class ClienteDTO {

    @Schema(description = "Identificador do Cliente", example = "1")
    private Long id;

    @NotBlank(message = "O nome não pode estar vazio")
    @Size(min = 3, max = 50, message = "O nome deve ter entre 3 e 50 caracteres")
    @Pattern(regexp = "^[a-zA-ZÀ-ÿ\\s]+$", message = "O nome deve conter apenas letras e espaços")
    @Schema(description = "Nome do cliente", example = "Ana Beatriz")
    private String nome;

    @NotNull(message = "O telefone não pode ser nulo")
    @Positive(message = "O telefone deve conter apenas números positivos")
    @Schema(description = "Número de telefone do cliente", example = "11999999999")
    private Long telefone;

    @NotNull(message = "Correntista não pode ser nulo")
    @Schema(description = "Indica se o cliente é correntista", example = "true")
    private Boolean correntista;

    @NotNull(message = "O saldo não pode ser nulo")
    @DecimalMin(value = "0.0", inclusive = true, message = "O saldo deve ser maior ou igual a zero")
    @Schema(description = "Saldo na conta corrente", example = "1000.00")
    private Float saldoCc;

    @Schema(description = "Score de crédito calculado automaticamente", example = "100.00")
    private Float scoreCredito;

    public ClienteDTO() {}

    @JsonCreator
    public ClienteDTO(
            @JsonProperty("id") Long id,
            @JsonProperty("nome") String nome,
            @JsonProperty("telefone") Long telefone,
            @JsonProperty("correntista") Boolean correntista,
            @JsonProperty("saldoCc") Float saldoCc) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.correntista = correntista;
        this.saldoCc = (correntista != null && correntista) ? saldoCc : 0f;
        this.scoreCredito = calcularScoreCredito();
    }

    private float calcularScoreCredito() {
        return saldoCc != null ? saldoCc * 0.1f : 0f;
    }


    public Long getId() { return id; }
    public String getNome() { return nome; }
    public Long getTelefone() { return telefone; }
    public Boolean getCorrentista() { return correntista; }
    public Float getSaldoCc() { return saldoCc; }
    public Float getScoreCredito() { return scoreCredito; }

    public void setNome(String nome) { this.nome = nome; }
    public void setTelefone(Long telefone) { this.telefone = telefone; }

    public void setCorrentista(Boolean correntista) {
        this.correntista = correntista;
        if (Boolean.FALSE.equals(correntista)) {
            this.saldoCc = 0f; // Se não for correntista, saldo = 0
        }
    }

    public void setSaldoCc(Float saldoCc) {
        this.saldoCc = saldoCc;
        this.scoreCredito = calcularScoreCredito();
    }

    @JsonIgnore
    public void setId(Long id) {
        if (this.id != null && !this.id.equals(id)) {
            throw new UnsupportedOperationException("O ID do cliente não pode ser alterado.");
        }
    }
}
