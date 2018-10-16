package com.dbms.studentCourseGrade;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentCourseGradeRepository extends JpaRepository<StudentCourseGrade, StudentCourseGradeId> {

     public List<StudentCourseGrade> findAllByOrderByScgIdAsc();
    
} //StudentCourseGradeRepository
