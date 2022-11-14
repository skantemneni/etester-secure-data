package com.etester.data.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.etester.data.domain.test.Test;
import com.etester.data.domain.test.TestDao;
import com.etester.data.domain.test.TestResponse;
import com.etester.data.domain.test.TestWithResponse;
import com.etester.data.domain.test.instance.JdbcUsertestDao;
import com.etester.data.domain.test.instance.JdbcUsertestresponseDao;
import com.etester.data.domain.test.instance.Usertest;
import com.etester.data.domain.test.instance.Usertestresponse;
// import com.etester.data.domain.user.UserDao;

import lombok.extern.slf4j.Slf4j;

/**
 * Add @CrossOrigin("http://localhost:4200") to get Angular DEV Mode (running on http://localhost:4200 to talk to Spring Controller
 * @author skantemneni
 *
 */
@Slf4j
@RestController
// @CrossOrigin("http://localhost:4200")
@CrossOrigin
@RequestMapping("/data/usertest")
// @Profile("cloud")
public class EtesterUsertestController {

	private final JdbcUsertestDao usertestDao;
	private final JdbcUsertestresponseDao usertestresponseDao;
	private final TestDao testDao;

	public EtesterUsertestController(JdbcUsertestDao usertestDao, JdbcUsertestresponseDao usertestresponseDao, TestDao testDao) {
		this.usertestDao = usertestDao;
		this.usertestresponseDao = usertestresponseDao;
		this.testDao = testDao;
	}


	/**
	 * This retrieves the Test  for administering the test in the eTester User app.  
	 * @param httpServletResponse
	 * @param idTest
	 * @return
	 */
	@GetMapping("/test/get/{idTest}")
	Test findTestByTestId(HttpServletResponse httpServletResponse, @PathVariable Long idTest) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		log.info("User: {} is calling findTestById for Test with ID: {}", username, idTest);
		httpServletResponse.setStatus(HttpServletResponse.SC_OK);
		return testDao.findByTestId(idTest);
	}

	/**
	 * This retrieves the Usertest Response without actual Test object buried within.  This can help overlay 
	 * a previously saved response on a eTester rendered test.  
	 * @param httpServletResponse
	 * @param idTest
	 * @return
	 */
	@GetMapping("/currentuserresponsefortest/{idTest}")
	TestResponse findCurrentUserResponseForTest(HttpServletResponse httpServletResponse, @PathVariable Long idTest) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		log.info("User: {} is calling findTestById for Test with ID: {}", username, idTest);
		httpServletResponse.setStatus(HttpServletResponse.SC_OK);
		return this.usertestDao.findUsertestresponseForTest(idTest);
	}


	
	/**
	 * This retrieves the Test and Response for administering the test in the eTester User app.  
	 * @param httpServletResponse
	 * @param idUsertest
	 * @return
	 */
	@GetMapping("/usertestresponse/get/{idUsertest}")
	TestWithResponse findTestByUsertestIdWithResponse(HttpServletResponse httpServletResponse, @PathVariable Long idUsertest) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		log.info("User: {} is calling findTestByUsertestIdWithResponse for User test with ID: {}", username, idUsertest);
		httpServletResponse.setStatus(HttpServletResponse.SC_OK);
		return testDao.findTestByUsertestIdWithResponse(idUsertest);
	}

	/**
	 * This method saves the test response from administering the test in the eTester User app
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @param usertestresponse - Contains the actual response and the flag (completed) to indicate the test status. 
	 * 							 Completed=true triggers subsequent workflow for grading on an asynchronous thread  
	 * @return
	 */
	@PostMapping("/usertestresponse/save")
	@ResponseBody
	Usertestresponse saveUsertestResponse(HttpServletRequest httpServletRequest, 
			HttpServletResponse httpServletResponse, 
			@RequestBody Usertestresponse usertestresponse
			) {
		
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Long idUsertest = usertestresponse.getIdUsertest();
		log.info("User: {} is calling saveUsertestResponse for Usertest with ID: {}", username, idUsertest);
		this.usertestresponseDao.saveTestResponse(usertestresponse);
		httpServletResponse.setStatus(HttpServletResponse.SC_OK);
		return usertestresponse;
	}

	/**
	 * This method returns all user tests for a username
	 * @param httpServletResponse
	 * @return List<Usertest>
	 */
	@GetMapping("/allforcurrentuser")
	List<Usertest> findAllAssignedUsertestsForCurrentUser(HttpServletResponse httpServletResponse) {
		log.info("User: {} is calling findAllAssignedUsertestsForCurrentUser", SecurityContextHolder.getContext().getAuthentication().getName());
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		httpServletResponse.setStatus(HttpServletResponse.SC_OK);
		return findAllAssignedUsertestsForUsernameInternal(username);
	}
	@GetMapping("/allforusername/{username}")
	List<Usertest> findAllAssignedUsertestsForUsername(HttpServletResponse httpServletResponse, @PathVariable String username) {
		log.info("User: {} is calling findAllAssignedUsertestsForUsername for: " + username, SecurityContextHolder.getContext().getAuthentication().getName());
		httpServletResponse.setStatus(HttpServletResponse.SC_OK);
		return findAllAssignedUsertestsForUsernameInternal(username);
	}
	private List<Usertest> findAllAssignedUsertestsForUsernameInternal(String username) {
		return usertestDao.findAllAssignedUsertestsForUsername(username);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	@GetMapping("/usertest/{idUsertest}")
	Usertest findByUsertestId(HttpServletResponse httpServletResponse, @PathVariable Long idUsertest) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		log.info("User: {} is calling findByUsertestId for User test with ID: {}", username, idUsertest);
		httpServletResponse.setStatus(HttpServletResponse.SC_OK);
		return usertestDao.findByUsertestId(idUsertest);
	}

	@GetMapping("/userid/{id}")
	List<Usertest> findAllAssignedUsertestsForUserId(HttpServletResponse httpServletResponse, @PathVariable Long id) {
		log.info("User: {} is calling findAllAssignedUsertestsForUserId for: " + id, SecurityContextHolder.getContext().getAuthentication().getName());
		httpServletResponse.setStatus(HttpServletResponse.SC_OK);
		return usertestDao.findAllAssignedUsertestsForUserId(id);
	}
	
	
}
