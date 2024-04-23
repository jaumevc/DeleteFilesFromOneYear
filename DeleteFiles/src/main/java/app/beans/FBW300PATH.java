package app.beans;

public class FBW300PATH {
	private int idoper;
	private String reference;
	private String rootpath;
	private String movepath;
	private int limitdays;
	
	public FBW300PATH() {}
	public FBW300PATH(int idoper, String reference, String rootpath, String movepath, int limitdays) {
		super();
		this.idoper = idoper;
		this.reference = reference;
		this.rootpath = rootpath;
		this.movepath = movepath;
		this.limitdays= limitdays;
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

	public String getRootpath() {
		return rootpath;
	}
	public void setRootpath(String rootpath) {
		this.rootpath = rootpath;
	}
	public String getMovepath() {
		return movepath;
	}
	public void setMovepath(String movepath) {
		this.movepath = movepath;
	}
	public int getLimitdays() {
		return limitdays;
	}
	public void setLimitdays(int limitdays) {
		this.limitdays = limitdays;
	}
}
