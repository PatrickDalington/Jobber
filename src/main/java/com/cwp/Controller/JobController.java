package com.cwp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cwp.dto.JobRequestDTO;
import com.cwp.dto.JobResponseDTO;
import com.cwp.services.JobService;

import jakarta.validation.Valid;




@RestController
@RequestMapping("/api/jobs")
public class JobController {





	 /* Create */

	@Autowired
	JobService jobService;

	@PostMapping
	public ResponseEntity<?> postNewJob(@Valid @RequestBody JobRequestDTO jobDTO){

		try {
			return ResponseEntity
					.status(HttpStatus.CREATED)
					.body(jobService.postNewJob(jobDTO));
		}catch (RuntimeException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}


	 /* Read */

	@GetMapping
	public List<JobResponseDTO> getAllJobs(){
		return jobService.getAllJobs();
	}

	@GetMapping("/location/{city}")
	public List<JobResponseDTO> getJobsByLocation(@PathVariable String city){
		return jobService.getJobsByLocation(city);
	}

}
