package com.example.DevShowcase.dto;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import org.hibernate.validator.constraints.URL;

@Schema(description = "Dados para cadastro de um projeto")
public class ProjectRequestDTO {

    @NotBlank(message = "O título não pode estar em branco")
    @Schema(
            description = "Título do projeto",
            type = "string",
            example = "DevShowcase"
    )
    private String title;

    @NotBlank(message = "A descrição não pode estar em branco")
    @Schema(
            description = "Descrição do projeto",
            type = "string",
            example = "API para exibição de portfólios de desenvolvedores"
    )
    private String description;

    @NotBlank(message = "A URL do projeto não pode estar em branco")
    @URL(message = "A URL do projeto deve ser válida")
    @Schema(
            description = "URL do projeto",
            type = "string",
            example = "https://github.com/exemplo/devshowcase"
    )
    private String projectUrl;

    @NotNull(message = "O ID do perfil é obrigatório")
    @Schema(
            description = "ID do perfil responsável pelo projeto",
            type = "integer",
            format = "int64",
            example = "1"
    )
    private Long profileId;

    @ArraySchema(
            schema = @Schema(
                    type = "integer",
                    format = "int64",
                    example = "1"
            )
    )
    private List<Long> technologyIds;

    public ProjectRequestDTO() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProjectUrl() {
        return projectUrl;
    }

    public void setProjectUrl(String projectUrl) {
        this.projectUrl = projectUrl;
    }

    public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

    public List<Long> getTechnologyIds() {
        return technologyIds;
    }

    public void setTechnologyIds(List<Long> technologyIds) {
        this.technologyIds = technologyIds;
    }
}