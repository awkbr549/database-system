package com.dbms.studentDepartmentMajor;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentDepartmentMajorRepository extends JpaRepository<StudentDepartmentMajor, StudentDepartmentMajorId> {

    public List<StudentDepartmentMajor> findAllByOrderBySdmIdAsc();
    
} //StudentDepartmentMajorRepository
