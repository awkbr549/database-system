package com.dbms.studentCourseGrade;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.dbms.student.Student;
import com.dbms.course.Course;

@Entity
//@IdClass(StudentCourseGradeId.class)
@Table(name = "student_course_grade")
public class StudentCourseGrade implements Serializable {

    @EmbeddedId
    private StudentCourseGradeId scgId;
	
    @Column(name="grade")
    private String grade;

    public StudentCourseGradeId getScgId() {
	return scgId;
    } //getScgId

    public void setScgId(StudentCourseGradeId scgId) {
	this.scgId = scgId;
    } //setScgId
    
    public void setScgId(String sid, String cid) {
	StudentCourseGradeId tempScg = new StudentCourseGradeId();
	Student tempStudent = new Student();
	Course tempCourse = new Course();

	tempStudent.setSid(new Long(sid));
	tempCourse.setCid(new Long(cid));
	tempScg.setStudent(tempStudent);
	tempScg.setCourse(tempCourse);

	this.scgId = tempScg;
    } //setScgId
    
    public String getGrade() {
	return grade;
    }

    public void setGrade(String grade) {
	this.grade = grade;
    }

}
