package com.dbms.studentCourseGrade;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.dbms.student.Student;
import com.dbms.course.Course;

@Embeddable
public class StudentCourseGradeId implements Serializable {
    @ManyToOne(cascade = CascadeType.DETACH,
	       /*fetch = //default// FetchType.EAGER,*/
	       optional = false,
	       targetEntity = Student.class
	       )
    @JoinColumn(/*columnDefinition = default,*/
	        /*foreignKey = default,*/
		insertable = true,
		name ="sid",
		nullable = false,
		referencedColumnName = "sid",
		//table = "student",
		unique = false,
		updatable = false
		)
    private Student student;

    @ManyToOne(cascade = CascadeType.DETACH,
	       /*fetch = //default// FetchType.EAGER,*/
	       optional = false,
	       targetEntity = Course.class
	       )
    @JoinColumn(/*columnDefinition = default,*/
		/*foreignKey = default,*/
		insertable = true,
		name ="cid",
		nullable = false,
		referencedColumnName = "cid",
		//table = "course",
		unique = false,
		updatable = false
		)
    private Course course;

    public Student getStudent() {
	return student;
    }

    public void setStudent(Student student) {
	this.student = student;
    }

    public Course getCourse() {
	return course;
    }

    public void setCourse(Course course) {
	this.course = course;
    }
   
} //StudentCourseGradeId
