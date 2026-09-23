package com.example.DevShowcase.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados retornados de um feedback")
public class FeedbackResponseDTO {

    @Schema(
            description = "ID do feedback",
            type = "integer",
            format = "int64",
            example = "1"
    )
    private Long id;

    @Schema(
            description = "Comentário do feedback",
            type = "string",
            example = "Projeto bem estruturado e funcional"
    )
    private String comment;

    @Schema(
            description = "Nota atribuída ao projeto",
            type = "integer",
            example = "5"
    )
    private Integer rating;

    @Schema(
            description = "ID do projeto avaliado",
            type = "integer",
            format = "int64",
            example = "1"
    )
    private Long projectId;

    public FeedbackResponseDTO(
            Long id,
            String comment,
            Integer rating,
            Long projectId) {

        this.id = id;
        this.comment = comment;
        this.rating = rating;
        this.projectId = projectId;
    }

    public Long getId() {
        return id;
    }

    public String getComment() {
        return comment;
    }

    public Integer getRating() {
        return rating;
    }

    public Long getProjectId() {
        return projectId;
    }
}
