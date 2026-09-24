package com.example.DevShowcase.service;

import com.example.DevShowcase.dto.ProjectRequestDTO;
import com.example.DevShowcase.dto.ProjectResponseDTO;
import com.example.DevShowcase.dto.TechnologyResponseDTO;
import com.example.DevShowcase.exception.ResourceNotFoundException;
import com.example.DevShowcase.model.Profile;
import com.example.DevShowcase.model.Project;
import com.example.DevShowcase.model.Technology;
import com.example.DevShowcase.repository.ProfileRepository;
import com.example.DevShowcase.repository.ProjectRepository;
import com.example.DevShowcase.repository.TechnologyRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private TechnologyRepository technologyRepository;

    public Page<ProjectResponseDTO> listar(
            Long technologyId,
            Pageable pageable) {

        Page<Project> projetos;

        if (technologyId != null) {
            projetos = projectRepository.findByTechnologiesId(
                    technologyId,
                    pageable
            );
        } else {
            projetos = projectRepository.findAll(pageable);
        }

        return projetos.map(this::toResponseDTO);
    }

    public ProjectResponseDTO criar(ProjectRequestDTO requestDTO) {

        Profile profile = profileRepository
                .findById(requestDTO.getProfileId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Perfil não encontrado com id: "
                                + requestDTO.getProfileId()
                        )
                );

        Project project = new Project();

        project.setTitle(requestDTO.getTitle());
        project.setDescription(requestDTO.getDescription());
        project.setProjectUrl(requestDTO.getProjectUrl());
        project.setProfile(profile);

        project.setTechnologies(
                buscarTecnologias(requestDTO.getTechnologyIds())
        );

        Project projectSalvo = projectRepository.save(project);

        return toResponseDTO(projectSalvo);
    }

    @Transactional
    public ProjectResponseDTO incrementarUpvote(Long id) {

        Project project = buscarProjeto(id);

        project.setUpvotes(project.getUpvotes() + 1);

        Project projectAtualizado =
                projectRepository.save(project);

        return toResponseDTO(projectAtualizado);
    }

    private Project buscarProjeto(Long id) {

        return projectRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Projeto não encontrado com id: " + id
                        )
                );
    }

    private List<Technology> buscarTecnologias(
            List<Long> technologyIds) {

        if (technologyIds == null || technologyIds.isEmpty()) {
            return List.of();
        }

        List<Technology> technologies =
                technologyRepository.findAllById(technologyIds);

        long quantidadeIdsUnicos = technologyIds
                .stream()
                .distinct()
                .count();

        if (technologies.size() != quantidadeIdsUnicos) {
            throw new ResourceNotFoundException(
                    "Uma ou mais tecnologias informadas não foram encontradas"
            );
        }

        return technologies;
    }

    private ProjectResponseDTO toResponseDTO(Project project) {

        List<TechnologyResponseDTO> technologies =
                project.getTechnologies() == null
                ? List.of()
                : project.getTechnologies()
                        .stream()
                        .map(technology ->
                                new TechnologyResponseDTO(
                                        technology.getId(),
                                        technology.getName()
                                )
                        )
                        .toList();

        return new ProjectResponseDTO(
                project.getId(),
                project.getTitle(),
                project.getDescription(),
                project.getProjectUrl(),
                project.getProfile().getId(),
                technologies,
                project.getAverageRating(),
                project.getUpvotes()
        );
    }
    public void excluir(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Projeto não encontrado com id: " + id));
        
        projectRepository.delete(project);
    }
}
