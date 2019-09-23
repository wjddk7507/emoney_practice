package com.spring.junga.vo;

// 게시글 목록 하단에 출력되는 페이지 번호들을 출력하기 위함
public class PageMaker {
	private int totalCount; // 전체 데이터 개수
	private int startPage; // 시작하는 페이지 번호와 끝나는 페이지 번호
	private int endPage;
	private boolean prev; // 이전과 다음 링크 여부
	private boolean next;
	private int displayPageNum = 10;
	private Criteria criteria; // 이전에 설정된 옵션 값을 저장
	
	public int getTotalCount() {
		return totalCount;
	}
	// 전체 데이터 개수, 현재 페이지, 출력할 페이지 개수로 나머지를 계산
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		endPage = (int)(Math.ceil(criteria.getPage() / (double)displayPageNum)) * displayPageNum;
		startPage = endPage-displayPageNum+1;
		prev = startPage==1? false:true;
		// 끝나는 페이지 번호가 전체 데이터의 페이지 개수보다 크면 전체 데이터의 페이지 개수로 결정
		int pagesu = (int)(Math.ceil(totalCount/(double)criteria.getPerPageNum()));
		if(endPage > pagesu) {
			endPage = pagesu;
		}
		// 마지막 페이지 번호와 페이지 개수가 같으면 다음은 나올 필요 없음
		next = endPage == pagesu ? false:true;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public boolean isPrev() {
		return prev;
	}
	public void setPrev(boolean prev) {
		this.prev = prev;
	}
	public boolean isNext() {
		return next;
	}
	public void setNext(boolean next) {
		this.next = next;
	}
	public int getDisplayPageNum() {
		return displayPageNum;
	}
	public void setDisplayPageNum(int displayPageNum) {
		this.displayPageNum = displayPageNum;
	}
	public Criteria getCriteria() {
		return criteria;
	}
	public void setCriteria(Criteria criteria) {
		this.criteria = criteria;
	}
	@Override
	public String toString() {
		return "PageMaker [totalCount=" + totalCount + ", startPage=" + startPage + ", endPage=" + endPage + ", prev="
				+ prev + ", next=" + next + ", displayPageNum=" + displayPageNum + ", criteria=" + criteria + "]";
	}
}
