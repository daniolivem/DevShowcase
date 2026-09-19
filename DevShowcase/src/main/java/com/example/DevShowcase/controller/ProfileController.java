package com.example.DevShowcase.controller;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.DevShowcase.model.Profile;
import com.example.DevShowcase.repository.ProfileRepository;

@RestController 
@RequestMapping("/profiles")
public class ProfileController {
	
	private ProfileRepository profileRepository;

	ProfileController(ProfileRepository profileRepository) {
		this.profileRepository = profileRepository;
	}
	
	@GetMapping
	public List<Profile> getAllProfiles() {
		return profileRepository.findAll();
	}
	
	@PostMapping
	public Profile createProfile(@RequestBody Profile profile) {
		return profileRepository.save(profile);
	}

	public ProfileRepository getProfileRepository() {
		return profileRepository;
	}

	public void setProfileRepository(ProfileRepository profileRepository) {
		this.profileRepository = profileRepository;
	}
}