package com.app.beans;

public class Arguments {
	
	private String reference;
	private String username;
	private String password;
	
	public Arguments() {
	}
	public Arguments(String reference, String username,  String password) {
		super();
		this.reference = reference;
		this.password = username;
		this.password = password;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
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
}
