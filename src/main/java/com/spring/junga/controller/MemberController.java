package com.spring.junga.controller;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.junga.service.AccountService;
import com.spring.junga.vo.AccountVO;

@Controller
public class MemberController {
	@Autowired
	private SqlSession sqlSession;
	@Autowired
	private AccountService accountService;
	
	// 회원가입 창으로 이동
	@RequestMapping(value = "join.ja", method = RequestMethod.GET)
	public String join(Model model) {	
		return "member/join";
	}
	
	@RequestMapping(value = "join.ja", method = RequestMethod.POST)
	public String join(HttpServletRequest request) {
		accountService.join(request);
		return "redirect:/login.ja";
	}
	
	// Ajax
	@ResponseBody
	@RequestMapping(value = "idCheck", method = RequestMethod.GET)
	public String idCheck(HttpServletRequest request) {	
		String id = accountService.idCheck(request);
		if(id==null) return "true";
		else return "false";
	}
	@ResponseBody
	@RequestMapping(value = "nicknameCheck", method = RequestMethod.GET)
	public String nicknameCheck(HttpServletRequest request) {	
		String nickname = accountService.nicknameCheck(request);
		if(nickname==null) return "true";
		else return "false";
	}

	@RequestMapping(value = "account.ja", method = RequestMethod.GET)
	public String account(Model model) {
		List<AccountVO> list = accountService.list();
		model.addAttribute("list", list);
		return "account";
	}

	@RequestMapping(value = "user_typeUpdate.ja", method = RequestMethod.GET)
	public String user_typeUpdateView(Model model, HttpServletRequest request) {
		AccountVO account = accountService.account(request);
		model.addAttribute("account", account);
		return "member/user_typeUpdate";
	}
	
	@RequestMapping(value = "user_typeUpdate.ja", method = RequestMethod.POST)
	public String user_typeUpdate(Model model, HttpServletRequest request) {
		accountService.user_typeUpdate(request);
		return "redirect:/account.ja";
	}
	
}
