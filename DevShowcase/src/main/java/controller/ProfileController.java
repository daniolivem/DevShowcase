package controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import model.Profile;
import repository.ProfileRepository;

@RestController 
@RequestMapping("/profiles")

public class ProfileController {
	
	@Autowired
	private ProfileRepository profileRepository;
	
	@GetMapping
	public List<Profile> getAllProfiles() {
		return profileRepository.findAll();
	}
	
	@PostMapping
	public Profile createProfile(@RequestBody Profile profile) {
		return profileRepository.save(profile);
	}

	//getters and setters
	
	public ProfileRepository getProfilerepository() {
		return profileRepository;
	}

	public void setProfilerepository(ProfileRepository profilerepository) {
		this.profileRepository = profilerepository;
	}
	
	

}
