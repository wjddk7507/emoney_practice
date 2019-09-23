package com.spring.junga.vo;

public class LoginHistoryVO {
	private int accnt_id;
	private String is_mobile;
	private String ip;
	private String browser;
	private String os;
	
	public int getAccnt_id() {
		return accnt_id;
	}
	public void setAccnt_id(int accnt_id) {
		this.accnt_id = accnt_id;
	}
	public String getIs_mobile() {
		return is_mobile;
	}
	public void setIs_mobile(String is_mobile) {
		this.is_mobile = is_mobile;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getBrowser() {
		return browser;
	}
	public void setBrowser(String browser) {
		this.browser = browser;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	@Override
	public String toString() {
		return "LoginHistoryVO [accnt_id=" + accnt_id + ", is_mobile=" + is_mobile + ", ip=" + ip + ", browser="
				+ browser + ", os=" + os + "]";
	}	
}
