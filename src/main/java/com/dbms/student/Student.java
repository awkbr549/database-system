package com.dbms.student;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.dbms.studentCourseGrade.StudentCourseGradeId;
import com.dbms.studDept.StudentDeptMajor;

@Entity //This tells Hibernate to make a table out of this class
public class Student {
    /*
     *Attributes with Hibernate-specific controls.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(/*columnDefinition = default,*/
	    insertable = false,
	    /*length = default,*/
	    name = "sid",
	    nullable = false,
	    table = "student",
	    unique = true,
	    updatable = false
	    ) //what this will map to in a database
	    private Long sid;

    @Column(/*columnDefinintion = default,*/
	    insertable = true,
	    /*length = default,*/
	    name = "dob",
	    nullable = false,
	    table = "student",
	    unique = false,
	    updatable = true
	    ) //what this will map to in a database
	    private Date dob;

    @Column(/*columnDefinition = default,*/
	    insertable = true,
	    length = 63,
	    name = "fname",
	    nullable = false,
	    table = "student",
	    unique = false,
	    updatable = true
	    ) //what this will map to in a database
	    private String fname;

    @Column(/*columnDefinition = default,*/
	    insertable = true,
	    length = 63,
	    name = "lname",
	    nullable = false,
	    table = "student",
	    unique = false,
	    updatable = true
	    ) //what this will map to in a database
	    private String lname;

    @Column(/*columnDefinition = default,*/
	    insertable = true,
	    /*length = auto,*/
	    name = "gender",
	    nullable = true,
	    table = "student",
	    unique = false,
	    updatable = true
	    ) //what this will map to in a database
	    private Character gender;

    @Column(/*columnDefinition = default,*/
	    insertable = true,
	    length = 127,
	    name = "email",
	    nullable = false,
	    table = "student",
	    unique = true,
	    updatable = true
	    ) //what this will map to in a database
	    private String email;

    @Column(/*columnDefinition = default,*/
	    insertable = true,
	    length = 63,
	    name = "street",
	    nullable = true,
	    table = "student",
	    unique = false,
	    updatable = true
	    ) //what this will map to in a database
	    private String street;

    @Column(/*columnDefinition = default,*/
	    insertable = true,
	    length = 63,
	    name = "city",
	    nullable = false,
	    table = "student",
	    unique = false,
	    updatable = true
	    ) //what this will map to in a database
	    private String city;

    @Column(/*columnDefinition = default,*/
	    insertable = true,
	    length = 2,
	    name = "state",
	    nullable = false,
	    table = "student",
	    unique = false,
	    updatable = true
	    ) //what this will map to in a database
	    private String state;
    
    @Column(/*columnDefinition = default,*/
	    insertable = true,
	    length = 5,
	    name = "zip",
	    nullable = false,
	    table = "student",
	    unique = false,
	    updatable = true
	    ) //what this will map to in a database
	    private Integer zip;
    
    @Column(/*columnDefinition = default,*/
	    insertable = true,
	    length = 63,
	    name = "residency",
	    nullable = false,
	    table = "student",
	    unique = false,
	    updatable = true
	    ) //what this will map to in a database
	    private String residency;
    
    /*    @OneToMany(mappedBy = "student")
	  private List<StudentDeptMajor> studDeptMajorList;
    
	  @OneToMany(mappedBy = "student")
	  private List<StudentCourseGradeId> studCrsGradeList;
    */
    
    /*
     *Getters and setters.
     */
    public Long getSid() {
	return sid;
    } //getId
    public void setSid(Long sid) {
	this.sid = sid;
    } //setId
    public void setId(long sid) {
	this.sid = new Long(sid);
    } //setId

    public Date getDob() {
	return dob;
    } //getDob
    public void setDob(long date) {
	this.dob.setTime(date);
    } //setDob
    public void setDob(String date) {
	this.dob = Date.valueOf(date);
    } //setDob
    public void setDob(Date date) {
	this.dob = date;
    } //setDob
    
    public String getFname() {
	return fname;
    } //getFname
    public void setFname(String fname) {
	this.fname = fname;
    } //setFname

    public String getLname() {
	return lname;
    } //getLname
    public void setLname(String lname) {
	this.lname = lname;
    } //setLname

    public Character getGender() {
	return gender;
    } //getGender
    public void setGender(char gender) {
	this.gender = new Character(gender);
    } //setGender
    public void setGender(Character gender) {
	this.gender = gender;
    } //setGender
    public void setGender(String gender) {
	this.gender = gender.charAt(0);
    } //setGender

    public String getEmail() {
	return email;
    } //getEmail
    public void setEmail(String email) {
	this.email = email;
    } //setEmail

    public String getStreet() {
	return street;
    } //getStreet
    public void setStreet(String street) {
	this.street = street;
    } //setStreet

    public String getCity() {
	return city;
    } //getCity
    public void setCity(String city) {
	this.city = city;
    } //setCity

    public String getState() {
	return state;
    } //getState
    public void setState(String state) {
	this.state = state;
    } //setState

    public Integer getZip() {
	return zip;
    } //getZip
    public void setZip(String zip) {
    	this.zip = new Integer(zip.substring(0, 5));
    } //setZip
    public void setZip(Integer zip) {
	this.zip = zip;
    } //setZip
    public void setZip(int zip) {
	this.zip = new Integer(zip);
    } //setZip
    
    public String getResidency() {
	return residency;
    } //getResidency
    public void setResidency(String residency) {
	this.residency = residency;
    } //setResidency
    
    /*public List<StudentDeptMajor> getStudentDeptMajor() {
        return studDeptMajorList;
    }

    public void setStudentDeptMajor(List<StudentDeptMajor> studDeptMajorList) {
        this.studDeptMajorList = studDeptMajorList;
    }
    
    public List<StudentCourseGrade> getStudentCrsGrade() {
        return studCrsGradeList;
    }

    public void setStudentCrsGrade(List<StudentCourseGrade> studCrsGradeList) {
        this.studCrsGradeList = studCrsGradeList;
	}*/
    
    
} //Student
