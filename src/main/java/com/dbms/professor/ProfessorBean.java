package com.dbms.professor;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class ProfessorBean {
	
	private Long pid;

	private String dob;
	
	@Size(min=3, max=40,message = "First name must be between {min} and {max} characters.")
	private String fname;
	 
	private String lname;
	
	@Pattern(regexp = "^[M|F]{1}$", message ="Gender: M or F")
	private String gender;

	@NotEmpty(message="Enter email")
	private String email;
	 
	private String street;
	 
	private String city;
	
	@Size(min=2, max=2,message = "State must be 2 characters.")
	private String state;
	
	@Size(min=5, max=5,message = "Zip code must be 5 characters long")
	private String zip;
	
	private Long deptId;

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}
}
