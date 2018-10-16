package com.dbms.course;

public class CourseBean {
	
	private Long cid;

	private String cnumber;

	private String cname;
	
	private String description;

	private Short credits;
	 
	private String semester;

	private Long did;
	
	private Long pid;

	public Long getDid() {
		return did;
	}
	
	public void setDid(Long did) {
		this.did = did;
	}
	
	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}
	
	public Long getCid() {
		return cid;
	}
	
	public void setCid(Long cid) {
		this.cid = cid;
	}

	public String getCnumber() {
		return cnumber;
	}
	
	public void setCnumber(String cnumber) {
		this.cnumber = cnumber;
	}
	
	public String getCname() {
		return cname;
	}
	
	public void setCname(String cname) {
		this.cname = cname;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Short getCredits() {
		return credits;
	}
	
	public void setCredits(Short credits) {
		this.credits = credits;
	}
	
	public String getSemester() {
		return semester;
	}
	
	public void setSemester(String semester) {
		this.semester = semester;
	}
	
}
