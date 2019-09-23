package com.spring.junga.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.junga.vo.AccountVO;
import com.spring.junga.vo.LoginHistoryVO;

@Repository
public class AccountDaoMapper {
	@Autowired
	private SqlSession sqlSession;
	
	// 전체 데이터 가져오는 메소드
	public List<AccountVO> list() {
		return sqlSession.selectList("account.list");
	}
	
	// id 중복 검사하는 메소드
	public String idCheck(String id) {
		return sqlSession.selectOne("account.idCheck", id);
	}
	
	// nickname 중복 검사하는 메소드
	public String nicknameCheck(String nickname) {
		return sqlSession.selectOne("account.nicknameCheck", nickname);
	}
	
	// 회원가입을 위한 메소드
	public void join(AccountVO account) {
		sqlSession.insert("account.join", account);
	}
	
	// 네이버 회원가입을 위한 메소드
	public void naverJoin(AccountVO account) {
		sqlSession.insert("account.naverJoin", account);
	}
	// id 중복 검사하는 메소드
	public String naverIdCheck(String id) {
		return sqlSession.selectOne("account.naverIdCheck", id);
	}
	// 네이버 아이디 accnt_id 가져오기
	public int getAccnt_id(String id){
		return sqlSession.selectOne("account.getAccnt_id", id);
	}
		
	// 로그인
	public AccountVO login(Map<String, Object> loginMap) {
		return sqlSession.selectOne("account.login", loginMap);
	}
	
	// 로그인 히스토리 기록
	public void login_history(LoginHistoryVO loginHistory) {
		sqlSession.update("account.last_login", loginHistory.getAccnt_id());
		sqlSession.insert("account.login_history", loginHistory);
	}
	
	// accnt_id로 회원 정보 조회
	public AccountVO account(int accnt_id) {
		return sqlSession.selectOne("account.account", accnt_id);
	}
	
	// 회원정보 수정
	public void user_typeUpdate(AccountVO account) {
		sqlSession.update("account.user_typeUpdate", account);
	}
}
