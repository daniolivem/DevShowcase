package com.example.DevShowcase.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Dados para cadastro de um feedback")
public class FeedbackRequestDTO {

    @NotBlank(message = "O comentário não pode estar em branco")
    @Schema(
            description = "Comentário sobre o projeto",
            type = "string",
            example = "Projeto bem estruturado e funcional"
    )
    private String comment;

    @NotNull(message = "A nota é obrigatória")
    @Min(value = 1, message = "A nota mínima é 1")
    @Max(value = 5, message = "A nota máxima é 5")
    @Schema(
            description = "Nota atribuída ao projeto, de 1 a 5",
            type = "integer",
            example = "5"
    )
    private Integer rating;

    public FeedbackRequestDTO() {
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}
