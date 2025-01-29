package com.client.ana.javer.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Objeto de resposta para erros na API")
public class ErrorResponseDTO {

    @Schema(description = "Código HTTP do erro", example = "status")
    private int status;

    @Schema(description = "Mensagem resumida do erro", example = "Requisição inválida")
    private String error;

    @Schema(description = "Descrição detalhada do erro", example = "O campo 'nome' não pode estar vazio")
    private String message;

    @Schema(description = "Caminho da requisição que gerou o erro", example = "/api/clientes")
    private String path;

    public ErrorResponseDTO(int status, String error, String message, String path) {
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    public int getStatus() { return status; }
    public void setStatus(int status) { this.status = status; }

    public String getError() { return error; }
    public void setError(String error) { this.error = error; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getPath() { return path; }
    public void setPath(String path) { this.path = path; }
}

