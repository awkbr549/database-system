package com.dbms.professor;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {
	@Query(value="SELECT * FROM Professor p where p.did = :did",nativeQuery=true)
	public List<Professor> findProfInDept(@Param("did") Long did);

}
