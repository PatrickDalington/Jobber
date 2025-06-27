package com.cwp.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private long id;
	
	@NotBlank(message = "Name is required")
	private String name;
	
	
	@Email(message = "Invalid email format")
	@NotBlank(message = "Email is required")
	@Column(unique = true)
	private String email;
	
	
	@NotBlank(message = "Password is required")
	@Size(min = 6, message = "Password must be at least 6 characters")
	private String password;
	
	
	@NotBlank(message = "Role is required")
	private String role; // "Applicant", "Employer", "Admin"
	
	@OneToMany(mappedBy = "postedBy", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Job> jobs;
	
	// Let us generate getters and setters
	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }
	
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	
	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }
	
	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }
	
	public String getRole() { return role; }
	public void setRole(String role) { this.role = role; }
	
	public List<Job> getJobs() { return jobs; }
	public void setJobs(List<Job> jobs) { this.jobs = jobs; }
	
	
}
