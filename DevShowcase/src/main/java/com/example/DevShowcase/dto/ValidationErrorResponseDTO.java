package com.example.DevShowcase.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.Map;

@Schema(description = "Resposta de erro de validação da API")
public class ValidationErrorResponseDTO {

    @Schema(
            description = "Data e hora em que o erro ocorreu",
            example = "2026-09-22T13:51:35.6867805"
    )
    private LocalDateTime timestamp;

    @Schema(
            description = "Código HTTP do erro",
            example = "400"
    )
    private Integer status;

    @Schema(
            description = "Descrição do status HTTP",
            example = "Bad Request"
    )
    private String error;

    @Schema(
            description = "Mensagem geral do erro",
            example = "Erro de validação"
    )
    private String message;

    @Schema(
            description = "Campos que apresentaram erro de validação",
            example = """
                  {
                    "campo": "Mensagem de validação"
                  }
                  """
    )
    private Map<String, String> fields;

    public ValidationErrorResponseDTO(
            LocalDateTime timestamp,
            Integer status,
            String error,
            String message,
            Map<String, String> fields) {

        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.fields = fields;
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

    public Map<String, String> getFields() {
        return fields;
    }
}
