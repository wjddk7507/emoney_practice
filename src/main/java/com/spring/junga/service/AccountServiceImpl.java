package com.spring.junga.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.GenericTypeAwareAutowireCandidateResolver;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.spring.junga.dao.AccountDaoMapper;
import com.spring.junga.vo.AccountVO;
import com.spring.junga.vo.LoginHistoryVO;

@Service
public class AccountServiceImpl implements AccountService {
	@Autowired
	private AccountDaoMapper accountDao;
	
	@Override
	public List<AccountVO> list() {
		List<AccountVO> list = accountDao.list();
		return list;
	}

	@Override
	public String idCheck(HttpServletRequest request) {
		String id = request.getParameter("id");
		return accountDao.idCheck(id);
	}

	@Override
	public String nicknameCheck(HttpServletRequest request) {
		String nickname = request.getParameter("nickname");
		return accountDao.nicknameCheck(nickname);
	}

	@Override
	public void join(HttpServletRequest request) {
		String id = request.getParameter("id");
		String s_passwd = request.getParameter("s_passwd");
		String name = request.getParameter("name");
		String nickname = request.getParameter("nickname");
		String phone = request.getParameter("phone1")+"-"+request.getParameter("phone2")+"-"+request.getParameter("phone3");
		String user_type = request.getParameter("user_type");
		
		
		// 암호화
		MessageDigest digest; // 해시 알고리즘 사용을 위함
		try {
			digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(s_passwd.getBytes("UTF-8")); // byte[]로 리턴하기 때문에 byte코드로 인코딩  
			StringBuffer hexString = new StringBuffer();
			
			for(int i = 0; i < hash.length; i++) {
				String hex = Integer.toHexString(0xff & hash[i]);	// byte를 hex 값으로 변경
				if(hex.length() == 1) hexString.append('0');
				hexString.append(hex);
				s_passwd = hexString.toString();
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		AccountVO account = new AccountVO(); // 파라미터로 사용할 객체 생성
		account.setId(id);
		account.setS_passwd(s_passwd);
		account.setName(name);
		account.setNickname(nickname);
		account.setPhone(phone);
		account.setUser_type(user_type);
		
		System.out.println("service_join : account 가입 정보 : " + account.toString());
		
		accountDao.join(account);
	}

	@Override
	public AccountVO login(HttpServletRequest request) {
		Map<String, Object> loginMap = new HashMap<String, Object>();
		loginMap.put("id", request.getParameter("id"));
		
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(request.getParameter("s_passwd").getBytes("UTF-8"));
			StringBuffer hexString = new StringBuffer();
			
			for(int i = 0; i < hash.length; i++) {
				String hex = Integer.toHexString(0xff & hash[i]);
				if(hex.length() == 1) hexString.append('0');
				hexString.append(hex);
				loginMap.put("s_passwd", hexString.toString());
			}			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return accountDao.login(loginMap);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
	public void login_history(HttpServletRequest request, int accnt_id) {
		LoginHistoryVO loginHistory = new LoginHistoryVO();
		loginHistory.setAccnt_id(accnt_id);
		String agent = request.getHeader("User-Agent");
		loginHistory.setIs_mobile("false");
		
		// ip 입력
		loginHistory.setIp(request.getRemoteAddr());
		
		// 모바일, 브라우저 구분
		String browser = null;
		if (agent != null) {
			   if (agent.indexOf("Trident") > -1) {
			      browser = "MSIE";
			   } else if (agent.indexOf("Chrome") > -1) {
			      browser = "Chrome";
			   } else if (agent.indexOf("Opera") > -1) {
			      browser = "Opera";
			   } else if (agent.indexOf("iPhone") > -1 && agent.indexOf("Mobile") > -1) {
				  loginHistory.setIs_mobile("true");
				  browser = "iPhone";
			   } else if (agent.indexOf("Android") > -1 && agent.indexOf("Mobile") > -1) {
				  loginHistory.setIs_mobile("true");
			      browser = "Android";
			   }
		}
		loginHistory.setBrowser(browser);
		
		// OS 구분
		String os = null;
		if(agent.indexOf("NT 6.0") != -1) os = "Windows Vista/Server 2008";
		else if(agent.indexOf("NT 5.2") != -1) os = "Windows Server 2003";
		else if(agent.indexOf("NT 5.1") != -1) os = "Windows XP";
		else if(agent.indexOf("NT 5.0") != -1) os = "Windows 2000";
		else if(agent.indexOf("NT") != -1) os = "Windows NT";
		else if(agent.indexOf("9x 4.90") != -1) os = "Windows Me";
		else if(agent.indexOf("98") != -1) os = "Windows 98";
		else if(agent.indexOf("95") != -1) os = "Windows 95";
		else if(agent.indexOf("Win16") != -1) os = "Windows 3.x";
		else if(agent.indexOf("Windows") != -1) os = "Windows";
		else if(agent.indexOf("Linux") != -1) os = "Linux";
		else if(agent.indexOf("Macintosh") != -1) os = "Macintosh";
		else os = ""; 

		loginHistory.setOs(os);
		
		accountDao.login_history(loginHistory);
	}

	@Override
	public void naverJoin(AccountVO account) {
		accountDao.naverJoin(account);
	}

	@Override
	public String naverIdCheck(String id) {
		return accountDao.naverIdCheck(id);
	}

	@Override
	public int getAccnt_id(String id) {
		return accountDao.getAccnt_id(id);
	}

	@Override
	public AccountVO account(HttpServletRequest request) {
		String accnt_id = request.getParameter("accnt_id");
		return accountDao.account(Integer.parseInt(accnt_id));
	}

	@Override
	public void user_typeUpdate(HttpServletRequest request) {
		String accnt_id = request.getParameter("accnt_id");
		String s_passwd = request.getParameter("s_passwd");
		String name = request.getParameter("name");
		String nickname = request.getParameter("nickname");
		String phone = request.getParameter("phone");
		String user_type = request.getParameter("user_type");
		
		// 암호화
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(s_passwd.getBytes("UTF-8"));
			StringBuffer hexString = new StringBuffer();
			
			for(int i = 0; i < hash.length; i++) {
				String hex = Integer.toHexString(0xff & hash[i]);
				if(hex.length() == 1) hexString.append('0');
				hexString.append(hex);
				s_passwd = hexString.toString();
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		AccountVO account = new AccountVO();
		account.setAccnt_id(Integer.parseInt(accnt_id));
		account.setS_passwd(s_passwd);
		account.setName(name);
		account.setNickname(nickname);
		account.setPhone(phone);
		account.setUser_type(user_type);		
		
		accountDao.user_typeUpdate(account);
	}
	
}
