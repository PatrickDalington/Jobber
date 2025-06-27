package com.cwp.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cwp.model.Job;

public interface JobRepository extends JpaRepository<Job, Long>{

	List<Job> findByLocation(String location);

}
