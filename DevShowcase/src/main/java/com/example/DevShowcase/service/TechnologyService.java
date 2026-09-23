package com.example.DevShowcase.service;

import com.example.DevShowcase.dto.TechnologyRequestDTO;
import com.example.DevShowcase.dto.TechnologyResponseDTO;
import com.example.DevShowcase.model.Technology;
import com.example.DevShowcase.repository.TechnologyRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TechnologyService {

    @Autowired
    private TechnologyRepository technologyRepository;

    public List<TechnologyResponseDTO> listar() {

        return technologyRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public TechnologyResponseDTO criar(
            TechnologyRequestDTO requestDTO) {

        Technology technology = new Technology();

        technology.setName(requestDTO.getName());

        Technology technologySalva =
                technologyRepository.save(technology);

        return toResponseDTO(technologySalva);
    }

    private TechnologyResponseDTO toResponseDTO(
            Technology technology) {

        return new TechnologyResponseDTO(
                technology.getId(),
                technology.getName()
        );
    }
}
