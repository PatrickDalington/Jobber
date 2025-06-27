package com.cwp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cwp.Exceptions.EmailAlreadyExistsException;
import com.cwp.dto.UserRequestDTO;
import com.cwp.dto.UserResponseDTO;
import com.cwp.model.User;
import com.cwp.repository.UserRepository;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class UserService {
	
	
	@Autowired UserRepository userRepository;
	
	public ResponseEntity<UserResponseDTO> registerUser (UserRequestDTO userDTO) {
		
		    if (userRepository.findByEmail(userDTO.getEmail()) != null) {
		        throw new EmailAlreadyExistsException("User with email already exists");
		    }

		    User user = new User();
		    user.setName(userDTO.getName());
		    user.setEmail(userDTO.getEmail());
		    user.setPassword(userDTO.getPassword());
		    user.setRole(userDTO.getRole());

		    User savedUser = userRepository.save(user);

		    UserResponseDTO responseDTO = new UserResponseDTO(
		        savedUser.getId(), savedUser.getName(), savedUser.getEmail(), savedUser.getRole()
		    );

		    return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);

		}

}
