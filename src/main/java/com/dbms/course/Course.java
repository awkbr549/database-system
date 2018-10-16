package com.dbms.course;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.dbms.department.Department;
import com.dbms.professor.Professor;
import com.dbms.studentCourseGrade.StudentCourseGrade;

@Entity
@Table(name = "course")
public class Course {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(insertable = false,name = "cid", nullable = false,unique = true, updatable = false)
    private Long cid;

    @Column(insertable = true,name = "cnumber",nullable = false,unique = true, updatable = true)																									
    private String cnumber;

    @Column(insertable = true, length = 63, name = "cname", updatable = true) 
    private String cname;

    @Column(insertable = true, length = 63, name = "description", updatable = true) 
    private String description;

    @Column(insertable = true,name = "credits",updatable = true)
    private Short credits;

    @Column(insertable = true, length = 127, name = "semester", updatable = true) 
    private String semester;
	
    @ManyToOne
    @JoinColumn(name ="did")
    private Department department;
	
    @ManyToOne
    @JoinColumn(name ="pid")
    private Professor professor;
	
    /*@OneToMany(mappedBy = "student")
      private List<StudentCourseGrade> studCrsGradeList;*/

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

    public Department getDepartment() {
	return department;
    }

    public void setDepartment(Department department) {
	this.department = department;
    }

    public Professor getProfessor() {
	return professor;
    }

    public void setProfessor(Professor professor) {
	this.professor = professor;
    }
	
    /*public List<StudentCourseGrade> getStudentCrsGrade() {
      return studCrsGradeList;
      }

      public void setStudentCrsGrade(List<StudentCourseGrade> studCrsGradeList) {
      this.studCrsGradeList = studCrsGradeList;
      }*/


}
