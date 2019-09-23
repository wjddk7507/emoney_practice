package com.spring.junga.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.junga.dao.BoardDaoMapper;
import com.spring.junga.vo.BoardVO;
import com.spring.junga.vo.Criteria;
import com.spring.junga.vo.PageMaker;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardDaoMapper boardDao;
	
	@Override
	public void register(HttpServletRequest request) {
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		int accnt_id = Integer.parseInt(request.getParameter("accnt_id"));

		BoardVO board = new BoardVO();
		board.setTitle(title);
		board.setContent(content);
		board.setAccnt_id(accnt_id);
		
		System.out.println(board.toString());
		
		boardDao.register(board);
	}

	@Override
	public Map<String, Object> list(Criteria criteria) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<BoardVO> list = boardDao.list(criteria);
		// 마지막 페이지에 데이터가 1개 밖에 없을 때 그 데이터를 삭제하면 그 페이지의 데이터는 없다
		if(list.size()==0) {
			// 현재 페이지 번호를 1 감소시켜서 데이터를 다시 가져오기
			criteria.setPage(criteria.getPage()-1);
			list = boardDao.list(criteria);
		}
		// 게시물 목록을 Map에 저장
		map.put("list", list);
		
		// 페이저 번호 목록 만들기
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCriteria(criteria);
		// 전체 데이터 개수 저장
		pageMaker.setTotalCount(boardDao.totalCount());
		// 페이지 번호 목록 map에 저장
		map.put("pageMaker", pageMaker);
		
		return map;
	}

	@Override
	public BoardVO detail(HttpServletRequest request) {
		String board_num = request.getParameter("board_num");
		return boardDao.detail(Integer.parseInt(board_num));
	}

	@Override
	public BoardVO updateView(HttpServletRequest request) {
		String board_num = request.getParameter("board_num");
		return boardDao.detail(Integer.parseInt(board_num));
	}

	@Override
	public void update(HttpServletRequest request) {
		String board_num = request.getParameter("board_num");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		BoardVO board = new BoardVO();
		board.setBoard_num(Integer.parseInt(board_num));
		board.setTitle(title);
		board.setContent(content);
		
		boardDao.update(board);
	}

	@Override
	public void delete(HttpServletRequest request) {
		String board_num = request.getParameter("board_num");
		boardDao.delete(Integer.parseInt(board_num));
	}

}
