package com.spring.junga.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.spring.junga.service.AccountService;
import com.spring.junga.vo.AccountVO;
import com.spring.junga.vo.LoginHistoryVO;

@Controller
public class LoginController {
	@Autowired
	private SqlSession sqlSession;
	@Autowired
	private AccountService accountService;
	
	/* NaverLoginBO */
	private NaverLoginBO naverLoginBO;
	private String apiResult = null;
	
	@Autowired
	private void setNaverLoginBO(NaverLoginBO naverLoginBO) {
		this.naverLoginBO = naverLoginBO;
	}

	//네이버 로그인 성공시 callback호출 메소드
	@RequestMapping(value = "/callback", method = { RequestMethod.GET, RequestMethod.POST })
	public String callback(Model model, HttpServletRequest request, @RequestParam String code, @RequestParam String state, HttpSession session)
			throws IOException {
		OAuth2AccessToken oauthToken;
        oauthToken = naverLoginBO.getAccessToken(session, code, state);
        //로그인 사용자 정보를 읽어온다.
	    apiResult = naverLoginBO.getUserProfile(oauthToken);
		model.addAttribute("result", apiResult);
		
		JsonParser jsonParser = new JsonParser();
		JsonObject object = (JsonObject) ((JsonObject) jsonParser.parse(apiResult)).get("response");
		
		String nickname = object.get("nickname").getAsString();
		String id = object.get("id").getAsString();
		String name = object.get("name").getAsString();
		AccountVO account = new AccountVO();
		account.setId(id);
		account.setNickname(nickname);
		account.setName(name);
		
		if(accountService.naverIdCheck(id) == null) {
			accountService.naverJoin(account);				
		}
		account.setAccnt_id(accountService.getAccnt_id(id));
		session.setAttribute("login", account);
		System.out.println("로그인 성공 : "+account.toString());
		accountService.login_history(request, account.getAccnt_id()); // login_history 기록
		// 이전 요청 가져오기
		Object dest = session.getAttribute("dest");
		if(dest == null){
			return "redirect:/";				
		}else {
			return "redirect:/"+dest.toString();
		}
	}
	
	@RequestMapping(value = "login.ja", method = RequestMethod.GET)
	public String login(Model model, HttpSession session,HttpServletRequest request) {
		/* 네이버아이디로 인증 URL을 생성하기 위하여 naverLoginBO클래스의 getAuthorizationUrl메소드 호출 */
		String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);
		//https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=sE***************&
		//redirect_uri=http%3A%2F%2F211.63.89.90%3A8090%2Flogin_project%2Fcallback&state=e68c269c-5ba9-4c31-85da-54c16c658125
		model.addAttribute("url", naverAuthUrl);

		/* 생성한 인증 URL을 View로 전달 */
		return "member/login";
	}
	
	
	// 일반 로그인
	@RequestMapping(value = "login.ja", method = RequestMethod.POST)
	public String loginProcess(HttpSession session, HttpServletRequest request, RedirectAttributes attr) {
			if(session.getAttribute("login") != null); { //기존에 login이란 세션 값이 존재한다면
			session.removeAttribute("login"); // 기존 값 제거
		} 
		// 로그인이 성공하면 accountVO 객체를 반환
		AccountVO account = accountService.login(request);
		
		if(account != null) { // 로그인 성공			
			session.setAttribute("login", account); // 세션에 login이란 account를 저장
			System.out.println("로그인 성공 : "+account.toString());	
			accountService.login_history(request, account.getAccnt_id()); // login_history 기록
			
			// 이전 요청 가져오기
			Object dest = session.getAttribute("dest");
			if(dest == null){
				return "redirect:/";				
			}else {
				return "redirect:/"+dest.toString();
			}
			
		} else { // 로그인에 실패
			attr.addFlashAttribute("msg","로그인에 실패하였습니다");
			return "redirect:/login.ja";
		}
	}
	
	// 로그아웃
	@RequestMapping(value = "logout.ja", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.invalidate(); // 세션 초기화
		return "redirect:/login.ja";		
	}
	
}
