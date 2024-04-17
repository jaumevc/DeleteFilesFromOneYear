package com.app.model;

public class PathReference {
	private int idoper;
	private String reference;
	private String typeoper;
	private String deletepath;
	private String movepath;
	
	public PathReference() {}
	public PathReference(int idoper, String reference, String typeoper, String deletepath, String movepath) {
		super();
		this.idoper = idoper;
		this.reference = reference;
		this.typeoper = typeoper;
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
	public String getTypeoper() {
		return typeoper;
	}
	public void setTypeoper(String typeoper) {
		this.typeoper = typeoper;
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
