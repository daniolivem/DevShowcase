package com.example.DevShowcase.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.DevShowcase.dto.ProjectRequestDTO;
import com.example.DevShowcase.model.Profile;
import com.example.DevShowcase.model.Project;
import com.example.DevShowcase.model.Technology;
import com.example.DevShowcase.repository.ProfileRepository;
import com.example.DevShowcase.repository.ProjectRepository;
import com.example.DevShowcase.repository.TechnologyRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private TechnologyRepository technologyRepository;

    @GetMapping
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @GetMapping("/{id}")
    public Project getProjectById(@PathVariable Long id) {
        return projectRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Project not found with id: " + id));
    }

    @GetMapping("/profile/{profileId}")
    public List<Project> getProjectsByProfileId(@PathVariable Long profileId) {
        return projectRepository.findByProfileId(profileId);
    }

    @PostMapping
    public Project createProject(@Valid @RequestBody ProjectRequestDTO requestDTO) {
        Profile profile = profileRepository.findById(requestDTO.getProfileId())
            .orElseThrow(() -> new RuntimeException("Profile not found with id: " + requestDTO.getProfileId()));
        
        Project project = new Project();
        project.setTitle(requestDTO.getTitle());
        project.setDescription(requestDTO.getDescription());
        project.setProjectUrl(requestDTO.getProjectUrl());
        project.setProfile(profile);
        
        // Associa as tecnologias se forem enviadas no DTO
        if (requestDTO.getTechnologyIds() != null && !requestDTO.getTechnologyIds().isEmpty()) {
            List<Technology> technologies = technologyRepository.findAllById(requestDTO.getTechnologyIds());
            project.setTechnologies(technologies);
        }
        
        return projectRepository.save(project);
    }

    @PutMapping("/{id}")
    public Project updateProject(@PathVariable Long id, @RequestBody Project projectDetails) {
        Project project = projectRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Project not found with id: " + id));
        
        project.setTitle(projectDetails.getTitle());
        project.setDescription(projectDetails.getDescription());
        project.setProjectUrl(projectDetails.getProjectUrl());
        
        return projectRepository.save(project);
    }

    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable Long id) {
        Project project = projectRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Project not found with id: " + id));
        
        projectRepository.delete(project);
    }
}