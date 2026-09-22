package com.example.DevShowcase.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados retornados de um perfil")
public class ProfileResponseDTO {

    @Schema(
            description = "ID do perfil",
            type = "integer",
            format = "int64",
            example = "1"
    )
    private Long id;

    @Schema(
            description = "Nome do desenvolvedor",
            type = "string",
            example = "Nivaldo Oliveira"
    )
    private String name;

    @Schema(
            description = "Descrição do perfil do desenvolvedor",
            type = "string",
            example = "Desenvolvedor backend Java e Spring Boot"
    )
    private String bio;

    @Schema(
            description = "URL do perfil do GitHub",
            type = "string",
            example = "https://github.com/nivaldo"
    )
    private String githubUrl;

    public ProfileResponseDTO() {
    }

    public ProfileResponseDTO(Long id, String name, String bio, String githubUrl) {
        this.id = id;
        this.name = name;
        this.bio = bio;
        this.githubUrl = githubUrl;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBio() {
        return bio;
    }

    public String getGithubUrl() {
        return githubUrl;
    }
}
