package com.it.bo.server.dto;

public class LoginDto {
	
	private String username;
	private String password;
	private String redirectUrl;
	private boolean rememberMe;
	

	public LoginDto(String username, String password, String redirectUrl, boolean rememberMe) {
		this.username = username;
		this.password = password;
		this.redirectUrl = redirectUrl;
		this.rememberMe = rememberMe;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRedirectUrl() {
		return redirectUrl;
	}
	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}
	public boolean isRememberMe() {
		return rememberMe;
	}
	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}

}
