package com.cwp.repository;
import com.cwp.model.Job;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long>{
	
	List<Job> findByLocation(String location);

}
