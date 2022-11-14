package com.etester.data.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etester.data.domain.content.core.Channel;
import com.etester.data.domain.content.core.JdbcChannelDao;
import com.etester.data.domain.user.UserDao;

import lombok.extern.slf4j.Slf4j;

@RestController
//@CrossOrigin
@RequestMapping("/data/content")
@Slf4j
// @Profile("cloud")
public class EtesterCoreContentController {

	private final JdbcChannelDao channelDao;
	private final UserDao userDao;

	public EtesterCoreContentController(JdbcChannelDao channelDao, UserDao userDao) {
		this.channelDao = channelDao;
		this.userDao = userDao;
	}

	@GetMapping("/channel/{id}")
	Channel getChannelById(HttpServletResponse httpServletResponse, @PathVariable Long id) {
		log.info("User: {} is calling getChannelById", SecurityContextHolder.getContext().getAuthentication().getName());
		httpServletResponse.setStatus(HttpServletResponse.SC_OK);
		return channelDao.findByChannelId(id);
	}

	
//    @CrossOrigin
	@GetMapping("/channels")
	List<Channel> getAllChannels(HttpServletResponse httpServletResponse) {
		log.info("User: {} is calling getAllChannels", SecurityContextHolder.getContext().getAuthentication().getName());
		httpServletResponse.setStatus(HttpServletResponse.SC_OK);
		return channelDao.findAllChannels();
	}
}
