package model;


import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name="projects")

public class Project {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private String tipo;

	@ManyToOne
	@JoinColumn(name ="profile_id")
	private Profile profile;
	
	//getters
		public Long getId() {
			return id;
		}
		public String getName() {
			return name;
		}
		
		public String getTipo() {
			return tipo;
		}
		
		public Profile getProfile() {
			return profile;
		}
			
		//setters
		
		public void setId(Long id) {
			this.id=id;
		}
		
		public void setName(String name) {
			this.name=name;
		}
		
		public void setTipo(String tipo) {
			this.tipo=tipo;
		}
		
		public void setProfile(Profile profile) {
			this.profile = profile;
		}
		
		
	}
