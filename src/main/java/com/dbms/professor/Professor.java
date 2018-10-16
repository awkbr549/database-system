package com.dbms.professor;

import java.sql.Date;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.persistence.Table;

import com.dbms.course.Course;
import com.dbms.department.Department;

@Entity
@Table(name = "professor")
public class Professor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "pid", nullable = false,unique = true)
	private Long pid;

	@Column(length = 40, name = "fname", nullable = false) 
	private String fname;

	@Column(name = "lname") 
	private String lname;
	
	@Column(name = "dob")																									
	private Date dob;

	@Column(name = "gender")
	private String gender;

	@Column(name = "email",unique = true) 
	private String email;

	@Column(name = "street") 
	private String street;

	@Column(name = "city") 
	private String city;

	@Column(name = "state") 
	private String state;

	@Column(name = "zip") 
	private String zip;
	
	@ManyToOne
	@JoinColumn(name ="did")
	private Department department;
	
	//@OneToMany(mappedBy = "professor", cascade=CascadeType.ALL)
	@OneToMany(mappedBy = "professor")
	private Set<Course> courses;

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public Date getDob() {
		return dob;
	}
    public void setDob(Date dob) {
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

	public Department getDepartment() {
		return department;
	}

    public Department getDid() {
	return department;
    } //getDid

	public void setDepartment(Department department) {
		this.department = department;
	}
	
	@OneToMany(mappedBy = "professor")
    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
    
    //Sets pid=null in course table on deletion of pid in professor.
    @PreRemove
    private void preRemove() {
    	Iterator<Course> courseIter=courses.iterator();
    	while(courseIter.hasNext()) {
    		Course c=courseIter.next();
    		c.setProfessor(null);
    	}
    }
}
