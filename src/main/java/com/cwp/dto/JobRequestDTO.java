package com.cwp.dto;

import jakarta.validation.constraints.NotBlank;

public class JobRequestDTO {

	@NotBlank
	private String jobTitle;

	@NotBlank
	private String description;

	@NotBlank
	private String location;

	private Long userId; // This Id will hold the Id of the employer posting this job


	public void setJobTitle(String jobTitle) {

		this.jobTitle = jobTitle;

	}

	public String getJobTitle() {
		return jobTitle;
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


	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}






}
