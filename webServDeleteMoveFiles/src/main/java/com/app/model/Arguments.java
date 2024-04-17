package com.app.model;

public class Arguments {
	
	private String path;
	private String days;
	private String operationType;
	
	public Arguments() {
	}
	public Arguments(String path, String days, String operationType) {
		super();
		this.path = path;
		this.days = days;
		this.operationType = operationType;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getDays() {
		return days;
	}
	public void setDays(String days) {
		this.days = days;
	}
	public String getOperationType() {
		return operationType;
	}
	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

}
