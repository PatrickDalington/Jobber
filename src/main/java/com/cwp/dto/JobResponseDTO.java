package com.cwp.dto;

import java.time.LocalDate;

public class JobResponseDTO {

	private Long id;
	private String jobTitle;
	private String description;
	private String location;
	private LocalDate datePosted;
	private String employerName;
	private String employerEmail;


	// Creating a constructor
	public JobResponseDTO (
			Long id, String jobTitle, String description, String location, LocalDate datePosted,
			String employerName, String employerEmail

	){
		this.id = id;
		this.jobTitle = jobTitle;
		this.description = description;
		this.location = location;
		this.datePosted = datePosted;
		this.employerName = employerName;
		this.employerEmail = employerEmail;
	}





	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getJobTitle() {
		return jobTitle;
	}


	public void setJobTitle(String title) {
		this.jobTitle = title;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public LocalDate getDatePosted() {
		return datePosted;
	}


	public void setDatePosted(LocalDate datePosted) {
		this.datePosted = datePosted;
	}


	public String getEmployerName() {
		return employerName;
	}


	public void setEmployerName(String employerName) {
		this.employerName = employerName;
	}

	public String getEmployerEmail() {
		return employerEmail;
	}

	public void setEmployerEmail(String employerEmail) {
		this.employerEmail = employerEmail;
	}



}
