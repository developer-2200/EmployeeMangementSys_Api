package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Department;

import jakarta.transaction.Transactional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long>{
	@Transactional
	@Modifying
	@Query("UPDATE Department d SET d.avg_score = :ascore WHERE d.id = :id")
	void updateWithScore(@Param("ascore")Double ascore,@Param("id") Long id);
	
	@Query("SELECT d FROM Department d ORDER BY d.avg_score DESC")
	List<Department>getDepartmentList();

}
