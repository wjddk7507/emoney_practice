package com.spring.junga.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.junga.service.AccountService;
import com.spring.junga.service.AccountServiceImpl;
import com.spring.junga.vo.AccountVO;

import ch.qos.logback.classic.Logger;

@Controller
public class HomeController {
	private static final Log log = LogFactory.getLog(HomeController.class);
	
	@Autowired
	private SqlSession sqlSession;
	@Autowired
	private AccountService accountService;

	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {	
		log.info("#ex - info log");
		return "index";
	}
	
}
