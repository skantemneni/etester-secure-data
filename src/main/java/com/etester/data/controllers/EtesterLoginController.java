package com.etester.data.controllers;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.etester.data.domain.user.User;
import com.etester.data.domain.user.UserDao;

import lombok.extern.slf4j.Slf4j;

/**
 * Add @CrossOrigin("http://localhost:4200") to get Angular DEV Mode (running on http://localhost:4200 to talk to Spring Controller
 * Add @CrossOrigin to allow requests from ANY domain and port
 * @author skantemneni
 *
 */
@Slf4j
@RestController
//@CrossOrigin("http://localhost:4200")
@CrossOrigin
@RequestMapping("/logincontroller")
public class EtesterLoginController {

	private final UserDao userDao;

	public EtesterLoginController(UserDao userDao) {
		this.userDao = userDao;
	}

	
//	@Autowired
//	AuthenticationManager authenticationManager;

	@Autowired
	ServletContext servletContext; 
	
	@PostMapping("/register")
	ResponseEntity<String> register(HttpServletRequest httpServletRequest, 
			HttpServletResponse httpServletResponse, 
			@RequestParam("username") final String username, 
			@RequestParam("password") final String password,
			@RequestParam("email") final String emailAddress,
			@RequestParam("firstname") final String firstName,
			@RequestParam("lastname") final String lastName) {
		User user = new User(username, password, new ArrayList());
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmailAddress(emailAddress);
		
		
		// TODO: SESI - This is only a temporary measure.  To enable users by default.
		// It should be done via an elaborate email based confirmation process. 
		// user.setEnabled(false);
		user.setEnabled(true);
		
		
		try {
			String response = userDao.addUser(user);
			if (response == null) {
				// this next line automatically Logs in the user after a successful registration.  May niot be what we want to do.
				
//				authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(httpServletRequest.getParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY), 
//						httpServletRequest.getParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY),
//		           		new ArrayList<>()));

				return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			} else if ("SAVE_FAIL_DUPLICATE_USER".equals(response)) {
				log.error("Error Adding User: {}", response);
				return new ResponseEntity<String>(response, HttpStatus.BAD_REQUEST);
			} else {
				log.error("Error Adding User: {}", response);
				return new ResponseEntity<String>(response, HttpStatus.I_AM_A_TEAPOT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	@PostMapping("/login")
	ResponseEntity<String> login(HttpServletResponse httpServletResponse, 
			@RequestParam("username") final String username, 
			@RequestParam("password") final String password) {
		boolean userExists = userDao.doesUserExistByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		if (userExists) {
//			Collection<String> headers = httpServletResponse.getHeaderNames();
//			for (String header: headers) {
//				System.out.print("HeaderName: " + header);
//			}
			httpServletResponse.setStatus(HttpServletResponse.SC_OK);
			return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("SAVE_FAIL_UNKNOWN_USER", HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/userdetails")
	User findUserDetails (HttpServletResponse httpServletResponse, 
			@RequestParam("username") final String username) {
		boolean userExists = userDao.doesUserExistByUsername(username);
		User user = null;
		if (userExists) {
			log.info("Calling findByUsername for username: {}", username);
			httpServletResponse.setStatus(HttpServletResponse.SC_OK);
			user = userDao.findByUsername(username);
			return user;
		} else {
			log.info("User Not found for username: {}", username);
			return null;
		}
	}

	@GetMapping("/currentuserdetails")
	User findCurrentUserDetails (HttpServletResponse httpServletResponse) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		log.info("Calling findCurrentUserDetails for Current User: {}", username);
		httpServletResponse.setStatus(HttpServletResponse.SC_OK);
		return findUserDetails(httpServletResponse, username);
	}

	@PostMapping("/resetpassword")
	ResponseEntity<String> resetpassword( 
			HttpServletResponse httpServletResponse, 
			@RequestParam("username") final String username, 
			@RequestParam("newpassword") final String newpassword) {
		try {
			
			if (username == null || username.trim().length() == 0 || newpassword == null || newpassword.trim().length() == 0) {
				throw new Exception("Bad Request");
			}
			
			boolean userExists = userDao.doesUserExistByUsername(username);
			if (userExists) {
				Integer response = userDao.updateEncodedPassword(username, newpassword);
				if (response == 0) {
					return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
				} else {
					log.error("Error Updating User Password: {}", response);
					return new ResponseEntity<String>("FAIL", HttpStatus.BAD_REQUEST);
				}
			} else {
				return new ResponseEntity<String>("UPDATE_FAIL_NO_USER", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>("FAIL", HttpStatus.BAD_REQUEST);
		}

	}

}
