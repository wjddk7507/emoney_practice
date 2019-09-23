package com.spring.junga.vo;

import java.sql.Date;

public class BoardVO {
	private int board_num;
	private String title;
	private Date regdate;
	private String content;
	private int accnt_id;
	
	public int getBoard_num() {
		return board_num;
	}
	public void setBoard_num(int board_num) {
		this.board_num = board_num;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getAccnt_id() {
		return accnt_id;
	}
	public void setAccnt_id(int accnt_id) {
		this.accnt_id = accnt_id;
	}
	
	@Override
	public String toString() {
		return "BoardVO [board_num=" + board_num + ", title=" + title + ", regdate=" + regdate + ", content=" + content
				+ ", accnt_id=" + accnt_id + "]";
	}	
}
