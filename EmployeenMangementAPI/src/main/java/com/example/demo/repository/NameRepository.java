package com.example.demo.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Name;

import jakarta.transaction.Transactional;

@Repository
public interface NameRepository extends JpaRepository<Name,String>{

	@Query("SELECT n.name FROM Name n WHERE n.joinDate BETWEEN :joindate AND :enddate")
	List<String> gatNmaesOfJoiningDate(@Param("joindate")Date joinDate,@Param("enddate") Date endDate);

	@Query("SELECT n.name FROM Name n WHERE n.joinDate = :date AND  DATEDIFF(DATE(NOW()),DATE(:date))/365 > 5")
	String getResultDate(@Param("date")Date date);

	@Transactional
	@Modifying
	@Query("SELECT AVG(n.score) FROM Name n WHERE n.name IN (:names)")
	Double getAverageScore(@Param("names")List<String> names);

	@Query("SELECT n.name FROM Name n")
	List<String> findAllNames();
	
	@Transactional
	@Modifying
	@Query("UPDATE Name n SET n.notification = :text WHERE n.name IN (:name)")
	void sendNotification(@Param("text") String text,@Param("name")List<String> name);

	@Query("SELECT n.name FROM Name n ")
	List<String> getDuplicate(List<String> email);

	@Query("SELECT n.email FROM Name n GROUP BY n.email HAVING COUNT(n.email) > 1")
	List<String> getDuplicateEmails();
	
	@Query("SELECT n.name FROM Name n WHERE n.email IN (:emails)")
	List<String> getNameByEmailID(@Param("emails")List<String> emails);

}
