package com.example.DevShowcase.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.DevShowcase.model.Feedback;
import com.example.DevShowcase.model.Project;
import com.example.DevShowcase.repository.FeedbackRepository;
import com.example.DevShowcase.repository.ProjectRepository;

@RestController
@RequestMapping("/api/feedbacks")
public class FeedbackController {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping
    public List<Feedback> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }

    @PostMapping("/project/{projectId}")
    public Feedback createFeedbackForProject(@PathVariable Long projectId, @RequestBody Feedback feedback) {
        Project project = projectRepository.findById(projectId)
            .orElseThrow(() -> new RuntimeException("Project not found with id: " + projectId));
        
        feedback.setProject(project);
        return feedbackRepository.save(feedback);
    }
}