package com.example.DevShowcase.service;

import com.example.DevShowcase.dto.FeedbackRequestDTO;
import com.example.DevShowcase.dto.FeedbackResponseDTO;
import com.example.DevShowcase.exception.ResourceNotFoundException;
import com.example.DevShowcase.model.Feedback;
import com.example.DevShowcase.model.Project;
import com.example.DevShowcase.repository.FeedbackRepository;
import com.example.DevShowcase.repository.ProjectRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Transactional
    public FeedbackResponseDTO criar(
            Long projectId,
            FeedbackRequestDTO requestDTO) {

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Projeto não encontrado com id: " + projectId));

        Feedback feedback = new Feedback();

        feedback.setComment(requestDTO.getComment());
        feedback.setRating(requestDTO.getRating());
        feedback.setProject(project);

        Feedback feedbackSalvo = feedbackRepository.save(feedback);

        atualizarMediaProjeto(project);

        return toResponseDTO(feedbackSalvo);
    }

    private void atualizarMediaProjeto(Project project) {

        List<Feedback> feedbacks =
                feedbackRepository.findByProjectId(project.getId());

        double media = feedbacks.stream()
                .mapToInt(Feedback::getRating)
                .average()
                .orElse(0.0);

        project.setAverageRating(media);

        projectRepository.save(project);
    }

    private FeedbackResponseDTO toResponseDTO(Feedback feedback) {
        return new FeedbackResponseDTO(
                feedback.getId(),
                feedback.getComment(),
                feedback.getRating(),
                feedback.getProject().getId()
        );
    }
    public void excluir(Long id) {
        // 1. Verifica se o feedback existe (ou obtém-o para saber a qual projeto pertencia)
        Feedback feedback = feedbackRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Feedback não encontrado com id: " + id));
                
        // 2. Guarda a referência do projeto associado (se precisares de recalcular a média)
        Project project = feedback.getProject();

        // 3. Apaga o feedback
        feedbackRepository.delete(feedback);

        // 4. (Opcional, mas recomendado) Atualiza a nota média do projeto se houver lógica para isso
        // atualizarMediaProjeto(project);
    }
}
