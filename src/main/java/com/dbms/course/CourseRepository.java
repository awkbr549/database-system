package com.dbms.course;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
	
	@Query(value="SELECT * FROM course c join professor p on c.pid=p.pid where p.pid = :pid",nativeQuery=true)
	public List<Course> findCourses(@Param("pid") Long pid);
	
	

}
