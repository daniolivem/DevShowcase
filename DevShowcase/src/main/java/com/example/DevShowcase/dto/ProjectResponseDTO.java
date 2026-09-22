package com.example.DevShowcase.dto;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Schema(description = "Dados retornados de um projeto")
public class ProjectResponseDTO {

    @Schema(
            description = "ID do projeto",
            type = "integer",
            format = "int64",
            example = "1"
    )
    private Long id;

    @Schema(
            description = "Título do projeto",
            type = "string",
            example = "DevShowcase"
    )
    private String title;

    @Schema(
            description = "Descrição do projeto",
            type = "string",
            example = "API para exibição de portfólios de desenvolvedores"
    )
    private String description;

    @Schema(
            description = "URL do projeto",
            type = "string",
            example = "https://github.com/exemplo/devshowcase"
    )
    private String projectUrl;

    @Schema(
            description = "ID do perfil associado ao projeto",
            type = "integer",
            format = "int64",
            example = "1"
    )
    private Long profileId;

    @ArraySchema(
            schema = @Schema(
                    implementation = TechnologyResponseDTO.class
            )
    )
    private List<TechnologyResponseDTO> technologies;

    @Schema(
            description = "Nota média das avaliações do projeto",
            type = "number",
            format = "double",
            example = "4.0"
    )
    private Double averageRating;

    @Schema(
            description = "Quantidade de upvotes do projeto",
            type = "integer",
            example = "1"
    )
    private Integer upvotes;

    public ProjectResponseDTO(
            Long id,
            String title,
            String description,
            String projectUrl,
            Long profileId,
            List<TechnologyResponseDTO> technologies,
            Double averageRating,
            Integer upvotes) {

        this.id = id;
        this.title = title;
        this.description = description;
        this.projectUrl = projectUrl;
        this.profileId = profileId;
        this.technologies = technologies;
        this.averageRating = averageRating;
        this.upvotes = upvotes;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getProjectUrl() {
        return projectUrl;
    }

    public Long getProfileId() {
        return profileId;
    }

    public List<TechnologyResponseDTO> getTechnologies() {
        return technologies;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public Integer getUpvotes() {
        return upvotes;
    }
}
