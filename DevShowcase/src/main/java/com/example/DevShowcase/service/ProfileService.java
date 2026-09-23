package com.example.DevShowcase.service;

import com.example.DevShowcase.dto.ProfileRequestDTO;
import com.example.DevShowcase.dto.ProfileResponseDTO;
import com.example.DevShowcase.exception.ResourceNotFoundException;
import com.example.DevShowcase.model.Profile;
import com.example.DevShowcase.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    public ProfileResponseDTO buscarPorId(Long id) {

        Profile profile = profileRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Perfil não encontrado com id: " + id
                        ));

        return toResponseDTO(profile);
    }

    public ProfileResponseDTO criar(ProfileRequestDTO requestDTO) {

        Profile profile = new Profile();

        profile.setName(requestDTO.getName());
        profile.setBio(requestDTO.getBio());
        profile.setGithubUrl(requestDTO.getGithubUrl());

        Profile profileSalvo = profileRepository.save(profile);

        return toResponseDTO(profileSalvo);
    }

    private ProfileResponseDTO toResponseDTO(Profile profile) {
        return new ProfileResponseDTO(
                profile.getId(),
                profile.getName(),
                profile.getBio(),
                profile.getGithubUrl()
        );
    }
}
