package com.example.DevShowcase.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.DevShowcase.model.Technology;
import com.example.DevShowcase.repository.TechnologyRepository;

@RestController
@RequestMapping("/api/technologies")
public class TechnologyController {

    @Autowired
    private TechnologyRepository technologyRepository;

    @GetMapping
    public List<Technology> getAllTechnologies() {
        return technologyRepository.findAll();
    }

    @PostMapping
    public Technology createTechnology(@RequestBody Technology technology) {
        return technologyRepository.save(technology);
    }
}