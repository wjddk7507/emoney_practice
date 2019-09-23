package com.spring.junga.vo;
// 현재 페이지 번호, 페이지 당 출력할 데이터 개수, 페이지에 출력될 데이터의 시작 번호 저장
public class Criteria {
	private int page;
	private int perPageNum;
	private int pageStart;

	public Criteria() {
		page = 1;
		perPageNum = 10;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPerPageNum() {
		return perPageNum;
	}
	public void setPerPageNum(int perPageNum) {
		this.perPageNum = perPageNum;
	}
	public int getPageStart() {
		pageStart = (page-1)*perPageNum + 1;
		return pageStart;
	}
	
	@Override
	public String toString() {
		return "Criteria [page=" + page + ", perPageNum=" + perPageNum + ", pageStart=" + pageStart + "]";
	}
}
