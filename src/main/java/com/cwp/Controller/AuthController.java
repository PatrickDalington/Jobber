package com.cwp.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cwp.model.User;
import com.cwp.repository.UserRepository;
import com.cwp.security.JwtUtil;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Map<String, String> loginData){
		
		String email  = loginData.get("email");
		String password = loginData.get("password");
		
		
		User user = userRepo.findByEmail(email);
		
		if (user == null || !user.getPassword().equals(password)) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credencials");
		}
		
		
		String token = jwtUtil.genrateToken(email, user.getRole());
		
		Map<String, Object> response = new HashMap<>();
		response.put("token", token);
		response.put("role", user.getRole());
		
		
		return ResponseEntity.ok(response);
		
	}

}
