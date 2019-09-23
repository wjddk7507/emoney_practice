package com.spring.junga.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.spring.junga.vo.BoardVO;
import com.spring.junga.vo.Criteria;

public interface BoardService {
	public void register(HttpServletRequest request);
	public Map<String, Object> list(Criteria criteria);
	public BoardVO detail(HttpServletRequest request);
	public BoardVO updateView(HttpServletRequest request);
	public void update(HttpServletRequest request);
	public void delete(HttpServletRequest request);
}
