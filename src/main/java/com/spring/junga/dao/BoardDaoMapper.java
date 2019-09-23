package com.spring.junga.dao;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.junga.vo.BoardVO;
import com.spring.junga.vo.Criteria;

@Repository
public class BoardDaoMapper {
	@Autowired
	private SqlSession sqlSession;
	
	public void register(BoardVO board) {
		sqlSession.insert("board.register", board);
	}
	
	public List<BoardVO> list(Criteria criteria) {
		return sqlSession.selectList("board.list", criteria);
	}
	
	public BoardVO detail(int board_num) {
		return sqlSession.selectOne("board.detail", board_num);
	}	
	
	public void update(BoardVO board) {
		sqlSession.update("board.update", board);
	}
	
	public void delete(int board_num) {
		sqlSession.delete("board.delete", board_num);
	}
	
	public int totalCount() {
		return sqlSession.selectOne("board.totalCount");
	}
	
}
