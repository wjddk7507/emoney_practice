package com.spring.junga.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.junga.service.BoardService;
import com.spring.junga.vo.BoardVO;
import com.spring.junga.vo.Criteria;

@Controller
public class BoardController {
	@Autowired
	private SqlSession sqlSession;
	@Autowired
	private BoardService boardService;

	@RequestMapping(value = "register.ja", method = RequestMethod.GET)
	public String board(Model model) {
		return "board/register";
	}
	@ResponseBody
	@RequestMapping(value = "register.ja", method = RequestMethod.POST)
	public String register(Model model, HttpServletRequest request) {
		boardService.register(request);
		return "true";
	}
		
	@RequestMapping(value = "list.ja", method = RequestMethod.GET)
	public String list(Model model, Criteria criteria) {
		Map<String, Object> map = boardService.list(criteria);
		model.addAttribute("map", map);
		return "board/list";
	}
	
	@RequestMapping(value = "detail.ja", method = RequestMethod.GET)
	public String detail(Model model, HttpServletRequest request) {
		BoardVO board = boardService.detail(request);
		model.addAttribute("board", board);
		return "board/detail";
	}

	@RequestMapping(value = "update.ja", method = RequestMethod.GET)
	public String updateView(Model model, HttpServletRequest request) {
		BoardVO board = boardService.detail(request);
		model.addAttribute("board", board);
		return "board/update";
	}
	
	@ResponseBody
	@RequestMapping(value = "update.ja", method = RequestMethod.POST)
	public String update(Model model, HttpServletRequest request) {
		boardService.update(request);	
		return "true";
	}
	
	@RequestMapping(value = "delete.ja", method = RequestMethod.GET)
	public String delete(Model model, HttpServletRequest request) {
		boardService.delete(request);	
		return "redirect:list.ja";
	}
	
}
