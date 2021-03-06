package com.dbms.studentDepartmentMajor;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.dbms.student.Student;
import com.dbms.department.Department;

@Entity
@Table(name = "student_department_major")
public class StudentDepartmentMajor implements Serializable {

    @EmbeddedId
    private StudentDepartmentMajorId sdmId;

    @Column(name = "major")
    private String major;

    public StudentDepartmentMajorId getSdmId() {
	return sdmId;
    } //getSdmId

    public void setSdmId(StudentDepartmentMajorId sdmId) {
	this.sdmId = sdmId;
    } //setSdmId
    
    public void setSdmId(String sid, String did) {
	StudentDepartmentMajorId tempSdm = new StudentDepartmentMajorId();
	Student tempS = new Student();
	Department tempD = new Department();

	tempS.setSid(new Long(sid));
	tempD.setDid(new Long(did));

	tempSdm.setStudent(tempS);
	tempSdm.setDepartment(tempD);

	this.sdmId = tempSdm;
    } //setSdmId

    public String getMajor() {
	return major;
    } //getMajor

    public void setMajor(String major) {
	this.major = major;
    } //setMajor
} //StudentDepartmentMajor
