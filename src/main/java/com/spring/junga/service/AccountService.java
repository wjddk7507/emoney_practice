package com.spring.junga.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.spring.junga.vo.AccountVO;

public interface AccountService {
	public List<AccountVO> list();
	public String idCheck(HttpServletRequest request);
	public String nicknameCheck(HttpServletRequest request);
	public void join(HttpServletRequest request);
	public void naverJoin(AccountVO account);
	public String naverIdCheck(String id);
	public int getAccnt_id(String id);
	public AccountVO login(HttpServletRequest request);
	public void login_history(HttpServletRequest request, int accnt_id);
	public AccountVO account(HttpServletRequest request);
	public void user_typeUpdate(HttpServletRequest request);
}
