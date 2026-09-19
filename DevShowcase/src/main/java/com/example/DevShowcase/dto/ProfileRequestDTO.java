package com.example.DevShowcase.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

public class ProfileRequestDTO {

    @NotBlank(message = "O nome não pode estar em branco")
    private String name;

    @NotBlank(message = "A bio não pode estar em branco")
    private String bio;

    @URL(message = "A URL do GitHub deve ser válida")
    private String githubUrl;

    // Getters e Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }

    public String getGithubUrl() { return githubUrl; }
    public void setGithubUrl(String githubUrl) { this.githubUrl = githubUrl; }
}