package com.dbms.department;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.dbms.course.Course;
import com.dbms.professor.Professor;
import com.dbms.studDept.StudentDeptMajor;

@Entity
@Table(name = "department")
public class Department {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(insertable = false, name = "did", nullable = false, unique = true, updatable = false)
	private Long did;

	@Size(min=1, max=60,message = "Department name must be between {min} and {max} characters.")
	@Column(name = "dname", nullable = false,unique=true)
	private String deptName;

	@Column(name = "description")
	private String deptDesc;
	
	@OneToMany(mappedBy = "department")
	private Set<Professor> professors;
	
	@OneToMany(mappedBy = "department")
	private Set<Course> courses;
	
	 @OneToMany(mappedBy = "department")
	 private List<StudentDeptMajor> studDeptMajorList;
	
	public Long getDid() {
		return did;
	}

	public void setDid(Long deptId) {
		this.did = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDeptDesc() {
		return deptDesc;
	}

	public void setDeptDesc(String deptDesc) {
		this.deptDesc = deptDesc;
	}
	
	@OneToMany(mappedBy = "department")
    public Set<Professor> getProfessors() {
        return professors;
    }

    public void setProfessors(Set<Professor> prof) {
        this.professors = prof;
    }
    
    @OneToMany(mappedBy = "department")
    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
    
    public List<StudentDeptMajor> getStudentDeptMajor() {
        return studDeptMajorList;
    }

    public void setStudentDeptMajor(List<StudentDeptMajor> studDeptMajorList) {
        this.studDeptMajorList = studDeptMajorList;
    }
}
