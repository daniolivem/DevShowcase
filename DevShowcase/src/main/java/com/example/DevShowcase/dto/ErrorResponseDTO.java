package com.example.DevShowcase.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

@Schema(description = "Resposta de erro da API")
public class ErrorResponseDTO {

    @Schema(
            description = "Data e hora em que o erro ocorreu",
            example = "2026-09-22T14:03:27.8478373"
    )
    private LocalDateTime timestamp;

    @Schema(
            description = "Código HTTP do erro",
            example = "404"
    )
    private Integer status;

    @Schema(
            description = "Descrição do status HTTP",
            example = "Not Found"
    )
    private String error;

    @Schema(
            description = "Mensagem detalhada do erro",
            example = "Projeto não encontrado com id: 999"
    )
    private String message;

    public ErrorResponseDTO(
            LocalDateTime timestamp,
            Integer status,
            String error,
            String message) {

        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }
}
