package com.spring.junga.util.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	// 컨트롤러보다 먼저 수행되는 메소드
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession(); // session 가져옴
		
		if(session.getAttribute("login")==null) { // 로그인 X
			// 로그인 되면 원래의 요청을 처리하기 위한 과정
			String requestURI = request.getRequestURI(); // 클라이언트의 요청 주소
			String contextPath = request.getContextPath(); // 서버의 주소
			String uri = requestURI.substring(contextPath.length()+1);  //주소 만들기
			String query = request.getQueryString(); // 주소 뒤에 파라미터 가져오기
			if(query == null || query.equals("null")) {
				query ="";
			} else {
				query = "?"+query;
			}
			System.out.println("인터셉터 : "+uri+query);
			session.setAttribute("dest", uri+query);
			response.sendRedirect("login.ja");
			return false;
		} else { // 로그인 O 
			return true;
		}
	}

}
