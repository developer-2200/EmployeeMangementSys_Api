package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Employee;

import jakarta.transaction.Transactional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

	@Query("SELECT SUM(e.salary) FROM Employee e WHERE e.department.id = :department")
	Double getSalaryByDepartmentId(@Param("department")Long id);

	@Query("SELECT e FROM Employee e WHERE e.name.name IN (:names)")
	List<Employee> getEmployeeByNames(@Param("names")List<String> names);

	@Query("SELECT AVG(e.salary) FROM Employee e WHERE e.post = :post")
	Double getAverageSalary(@Param("post")int position);

	@Query("SELECT AVG(e.salary) FROM Employee e WHERE e.department.id = :departmentid")
	Double getAverageSalaryByDepartmentId(@Param("departmentid")Long id);

	@Transactional
	@Modifying
	@Query("SELECT e.name FROM Employee e WHERE e.department.id = :dpid")
	List<String> getEmployeesByDepartmentId(@Param("dpid")Long id);

	@Query("SELECT e FROM Employee e ORDER BY e.salary DESC LIMIT :num")
	List<Employee> getEmployeeInOrder(@Param("num")int n);

}
