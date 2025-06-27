package com.cwp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cwp.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	User findByEmail(String email);

}
