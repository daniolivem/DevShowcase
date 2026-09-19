package model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "profiles")

public class Profile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private String email;
	
	@OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
	private List<Project> projects;
	
	//getters
	
	public String getName() {
		return name;
	}
	
	public String getEmail() {
		return email;
	}
	
	//setters
	
	public void setName(String name) {
		this.name=name;
	}
	
	public void setEmail(String email) {
		this.email=email;
	}
	
	
}
