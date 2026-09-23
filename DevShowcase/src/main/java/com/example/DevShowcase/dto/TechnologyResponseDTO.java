package com.example.DevShowcase.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados retornados de uma tecnologia")
public class TechnologyResponseDTO {

    @Schema(
            description = "ID da tecnologia",
            type = "integer",
            format = "int64",
            example = "1"
    )
    private Long id;

    @Schema(
            description = "Nome da tecnologia",
            type = "string",
            example = "Java"
    )
    private String name;

    public TechnologyResponseDTO() {
    }

    public TechnologyResponseDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
