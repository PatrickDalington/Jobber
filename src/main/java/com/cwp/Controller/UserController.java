package com.cwp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cwp.dto.UserRequestDTO;
import com.cwp.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping
	public ResponseEntity<?> registerUser(@Valid @RequestBody UserRequestDTO userDTO) {
	    try {
	       return ResponseEntity
	    		   .status(HttpStatus.CREATED)
	    		   .body(userService.registerUser(userDTO));
	    } catch (DataIntegrityViolationException ex) {
	        return ResponseEntity
	                .status(HttpStatus.CONFLICT)
	                .body("Duplicate entry: email already exists.");
	    } catch (Exception ex) {
	        return ResponseEntity
	                .status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("An error occurred: " + ex.getMessage());
	    }
	}



}
