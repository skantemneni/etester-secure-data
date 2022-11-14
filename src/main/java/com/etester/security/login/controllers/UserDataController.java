package com.etester.security.login.controllers;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.etester.security.login.models.User;
import com.etester.security.login.repository.JdbcUserRepository;
import com.etester.security.login.repository.RoleRepository;
import com.etester.security.login.security.jwt.JwtUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserDataController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JdbcUserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@GetMapping("/userdetails")
	public ResponseEntity<User> findUserDetails (HttpServletResponse httpServletResponse, 
			@RequestParam("username") final String username) {
		boolean userExists = userRepository.existsByUsername(username);
		Optional<User> user = null;
		if (userExists) {
			log.info("Calling findByUsername for username: {}", username);
//			httpServletResponse.setStatus(HttpServletResponse.SC_OK);
			user = userRepository.findByUsername(username);
			if (user.isPresent()) {
				return ResponseEntity.ok().body(user.get());
			}
		}
		log.info("User Not found for username: {}", username);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}

	@GetMapping("/currentuserdetails")
	ResponseEntity<User> findCurrentUserDetails (HttpServletResponse httpServletResponse) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		log.info("Calling findCurrentUserDetails for Current User: {}", username);

		boolean userExists = userRepository.existsByUsername(username);
		Optional<User> user = null;
		if (userExists) {
			user = userRepository.findByUsername(username);
			if (user.isPresent()) {
				return ResponseEntity.ok().body(user.get());
			}
		}
		log.info("User Not found for username: {}", username);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//		return findUserDetails(httpServletResponse, username);
	}

//	@PostMapping("/resetpassword")
//	ResponseEntity<String> resetpassword( 
//			HttpServletResponse httpServletResponse, 
//			@RequestParam("username") final String username, 
//			@RequestParam("newpassword") final String newpassword) {
//		try {
//			
//			if (username == null || username.trim().length() == 0 || newpassword == null || newpassword.trim().length() == 0) {
//				throw new Exception("Bad Request");
//			}
//			
//			boolean userExists = userRepository.existsByUsername(username);
//			if (userExists) {
//				Integer response = userDao.updateEncodedPassword(username, newpassword);
//				if (response == 0) {
//					return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
//				} else {
//					log.error("Error Updating User Password: {}", response);
//					return new ResponseEntity<String>("FAIL", HttpStatus.BAD_REQUEST);
//				}
//			} else {
//				return new ResponseEntity<String>("UPDATE_FAIL_NO_USER", HttpStatus.BAD_REQUEST);
//			}
//		} catch (Exception e) {
//			return new ResponseEntity<String>("FAIL", HttpStatus.BAD_REQUEST);
//		}
//
//	}

	
	
}
