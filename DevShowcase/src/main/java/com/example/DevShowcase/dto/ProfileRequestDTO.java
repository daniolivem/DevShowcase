package com.example.DevShowcase.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Schema(description = "Dados para cadastro de um perfil")
public class ProfileRequestDTO {

    @NotBlank(message = "O nome não pode estar em branco")
    @Schema(
            description = "Nome do desenvolvedor",
            type = "string",
            example = "Nivaldo Oliveira"
    )
    private String name;

    @NotBlank(message = "A bio não pode estar em branco")
    @Schema(
            description = "Descrição do perfil do desenvolvedor",
            type = "string",
            example = "Desenvolvedor backend Java e Spring Boot"
    )
    private String bio;

    @URL(message = "A URL do GitHub deve ser válida")
    @Schema(
            description = "URL do perfil do GitHub",
            type = "string",
            example = "https://github.com/nivaldo"
    )
    private String githubUrl;

    public ProfileRequestDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getGithubUrl() {
        return githubUrl;
    }

    public void setGithubUrl(String githubUrl) {
        this.githubUrl = githubUrl;
    }
}
