package com.dbms.studDept;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.dbms.department.Department;
import com.dbms.student.Student;

@Entity
@Table(name = "student_department_major")
public class StudentDeptMajor implements Serializable{
	
	@Id
	@ManyToOne
	@JoinColumn(name ="sid")
	private Student student;
	
	@Id
	@ManyToOne
	@JoinColumn(name ="did")
	private Department department;
	
	@Column(name="major")
	private String major;

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

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}
	
	/*public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		StudentDeptMajor that = (StudentDeptMajor) o;

		if (getPk() != null ? !getPk().equals(that.getPk())
				: that.getPk() != null)
			return false;

		return true;
	}

	public int hashCode() {
		return (getPk() != null ? getPk().hashCode() : 0);
	}*/

}
