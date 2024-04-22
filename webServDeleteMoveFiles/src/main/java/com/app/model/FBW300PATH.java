package com.app.model;

public class FBW300PATH/*PathReference*/ {
	private int idoper;
	private String reference;
	private String deletepath;
	private String movepath;
	
	public /*PathReference*/FBW300PATH() {}
	public /*PathReference*/FBW300PATH(int idoper, String reference, String deletepath, String movepath) {
		super();
		this.idoper = idoper;
		this.reference = reference;
		this.deletepath = deletepath;
		this.movepath = movepath;
	}
	public int getIdoper() {
		return idoper;
	}
	public void setIdoper(int idoper) {
		this.idoper = idoper;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getDeletepath() {
		return deletepath;
	}
	public void setDeletepath(String deletepath) {
		this.deletepath = deletepath;
	}
	public String getMovepath() {
		return movepath;
	}
	public void setMovepath(String movepath) {
		this.movepath = movepath;
	}
}
