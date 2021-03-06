package com.dbms.studentDepartmentMajor;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.dbms.student.Student;
import com.dbms.department.Department;

@Embeddable
public class StudentDepartmentMajorId implements Serializable {
    @ManyToOne(cascade = CascadeType.DETACH,
	       optional = false,
	       targetEntity = Student.class
	       )
    @JoinColumn(insertable = true,
		name = "sid",
		nullable = false,
		referencedColumnName = "sid",
		unique = false,
		updatable = false
		)
    private Student student;

    @ManyToOne(cascade = CascadeType.DETACH,
	       optional = false,
	       targetEntity = Department.class
	       )
    @JoinColumn(insertable = true,
		name = "did",
		nullable = false,
		referencedColumnName = "did",
		unique = false,
		updatable = false
		)
    private Department department;

    public Student getStudent() {
	return student;
    }

    public void setStudent(Student student) {
	this.student = student;
    }

    public Department getDepartment() {
	return department;
    }

    public void setDepartment(Department department) {
	this.department = department;
    } 
} //StudentDepartmentMajorId
