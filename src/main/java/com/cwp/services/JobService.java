package com.cwp.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cwp.dto.JobRequestDTO;
import com.cwp.dto.JobResponseDTO;
import com.cwp.model.Job;
import com.cwp.model.User;
import com.cwp.repository.JobRepository;
import com.cwp.repository.UserRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;


@Service
@Transactional
public class JobService {

	@Autowired
	private MailService mailService;

	@Autowired
	private JobRepository jobRepository;

	@Autowired
	private UserRepository userRepository;


	public JobService(JobRepository jobRepo, UserRepository userRepo) {
	      this.jobRepository = jobRepo;
	      this.userRepository = userRepo;
	}


	// SEARCH FOR A JOB BY LOCATION

	@GetMapping("/location/{city}")
	public List<JobResponseDTO> getJobsByLocation(@PathVariable String city){
		return jobRepository.findByLocation(city).stream().map(job -> new JobResponseDTO(

				job.getId(),
				job.getJobTitle(),
				job.getDescription(),
				job.getLocation(),
				job.getDatePosted(),
				job.getPostedBy().getName(),
				job.getPostedBy().getEmail()

		)).toList();
	}



	// Get all JOBS
	@GetMapping
	public List<JobResponseDTO> getAllJobs(){
		return jobRepository.findAll().stream()
                .map(job -> new JobResponseDTO(

        				job.getId(),
        				job.getJobTitle(),
        				job.getDescription(),
        				job.getLocation(),
        				job.getDatePosted(),
        				job.getPostedBy().getName(),
        				job.getPostedBy().getEmail()

        		)).toList();
	}


	// POST A JOB

	@PostMapping
	public ResponseEntity<?> postNewJob(@Valid @RequestBody JobRequestDTO j) {

		// In production get user after verify them from JWT or session

		 try {


		        //Long userId = j.getPostedBy().getId();
		        User employer = userRepository.findById(j.getUserId()).orElseThrow(
		        		() -> new RuntimeException("User not found"));

		        Job job = new Job();
		        job.setJobTitle(j.getJobTitle());
		        job.setDescription(j.getDescription());
		        job.setLocation(j.getLocation());
		        job.setPostedBy(employer);
		        job.setDatePosted(LocalDate.now());

		        Job savedJob = jobRepository.save(job);

		        JobResponseDTO response = new JobResponseDTO(

		        		savedJob.getId(),
		        		savedJob.getJobTitle(),
		        		savedJob.getDescription(),
		        		savedJob.getLocation(),
		        		savedJob.getDatePosted(),
		        		savedJob.getPostedBy().getName(),
		        		savedJob.getPostedBy().getEmail()
		        );

		        // Send email notification to this poster
		        mailService.sendJobPostedNotification(employer.getEmail(), savedJob.getJobTitle());

		        return ResponseEntity.status(HttpStatus.CREATED).body(response);

		    } catch (Exception e) {
		        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
		                .body("Failed to create job: " + e.getMessage());
		    }
	}
}
