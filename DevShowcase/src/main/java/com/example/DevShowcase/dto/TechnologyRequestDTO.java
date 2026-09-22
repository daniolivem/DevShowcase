package com.example.DevShowcase.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Dados para cadastro de uma tecnologia")
public class TechnologyRequestDTO {

    @NotBlank(message = "O nome da tecnologia não pode estar em branco")
    @Schema(
            description = "Nome da tecnologia",
            type = "string",
            example = "Java"
    )
    private String name;

    public TechnologyRequestDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
