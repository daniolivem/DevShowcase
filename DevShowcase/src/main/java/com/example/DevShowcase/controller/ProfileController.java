package com.example.DevShowcase.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.DevShowcase.model.Profile;
import com.example.DevShowcase.repository.ProfileRepository;

@RestController 
@RequestMapping("/api/profiles")
public class ProfileController {
	
	@Autowired
	private ProfileRepository profileRepository;
	
	@GetMapping
	public List<Profile> getAllProfiles() {
		return profileRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Profile getProfileById(@PathVariable Long id) {
		return profileRepository.findById(id)
			.orElseThrow(() -> new RuntimeException("Profile not found with id: " + id));
	}
	
	@PostMapping
	public Profile createProfile(@RequestBody Profile profile) {
		return profileRepository.save(profile);
	}

	@PutMapping("/{id}")
	public Profile updateProfile(@PathVariable Long id, @RequestBody Profile profileDetails) {
		Profile profile = profileRepository.findById(id)
			.orElseThrow(() -> new RuntimeException("Profile not found with id: " + id));
		
		profile.setName(profileDetails.getName());
		profile.setBio(profileDetails.getBio());
		profile.setGithubUrl(profileDetails.getGithubUrl());
		
		return profileRepository.save(profile);
	}

	@DeleteMapping("/{id}")
	public void deleteProfile(@PathVariable Long id) {
		Profile profile = profileRepository.findById(id)
			.orElseThrow(() -> new RuntimeException("Profile not found with id: " + id));
		
		profileRepository.delete(profile);
	}
}