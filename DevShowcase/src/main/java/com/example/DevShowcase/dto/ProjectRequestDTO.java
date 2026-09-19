package com.example.DevShowcase.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.URL;

public class ProjectRequestDTO {

    @NotBlank(message = "O título não pode estar em branco")
    private String title;

    @NotBlank(message = "A descrição não pode estar em branco")
    private String description;

    @URL(message = "A URL do projeto deve ser válida")
    private String projectUrl;

    @NotNull(message = "O ID do perfil é obrigatório")
    private Long profileId;

    // Getters e Setters
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getProjectUrl() { return projectUrl; }
    public void setProjectUrl(String projectUrl) { this.projectUrl = projectUrl; }

    public Long getProfileId() { return profileId; }
    public void setProfileId(Long profileId) { this.profileId = profileId; }
}