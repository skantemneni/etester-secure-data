package com.etester.data.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etester.data.dao.EtesterDataDao;
import com.etester.data.domain.user.UserDao;
import com.etester.data.entity.TestEntity;

import lombok.extern.slf4j.Slf4j;

/**
 * Add @CrossOrigin("http://localhost:4200") to get Angular DEV Mode (running on http://localhost:4200 to talk to Spring Controller
 * @author skantemneni
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/data")
@Slf4j
// @Profile("cloud")
public class EtesterRestController {

	private final EtesterDataDao etesterDataDao;
	private final UserDao userDao;

	public EtesterRestController(EtesterDataDao etesterDataDao, UserDao userDao
			) {
		this.etesterDataDao = etesterDataDao;
		this.userDao = userDao;
	}

	@GetMapping("/test/{id}")
	TestEntity getTestById(HttpServletResponse httpServletResponse, @PathVariable Long id) {
		log.info("User: {} is calling getTestById", SecurityContextHolder.getContext().getAuthentication().getName());
		boolean userExists = userDao.doesUserExistByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		if (userExists) {
			httpServletResponse.setStatus(HttpServletResponse.SC_OK);
			TestEntity testEntity = etesterDataDao.findTestById(id);
			log.info("Test: {}", testEntity == null ? "NULL" : testEntity.toString());
			return testEntity;
		} else {
			httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return null;
		}
	}

	
//    @CrossOrigin
	@GetMapping("/tests")
	List<TestEntity> getAllTests(HttpServletResponse httpServletResponse) {
		log.info("User: {} is calling getAllTests", SecurityContextHolder.getContext().getAuthentication().getName());
//		boolean userExists = userDao.doesUserExistByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
//		if (userExists) {
//			httpServletResponse.addHeader("Access-Control-Allow-Origin", "*");
			httpServletResponse.setStatus(HttpServletResponse.SC_OK);
			return etesterDataDao.findAllTests();
//		} else {
//			httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//			return null;
//		}
		
	}
}
