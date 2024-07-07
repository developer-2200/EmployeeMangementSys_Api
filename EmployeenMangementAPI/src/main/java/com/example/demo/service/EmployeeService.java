package com.example.demo.service;

import java.sql.Date;
import java.util.List;

import com.example.demo.entity.Department;
import com.example.demo.entity.Employee;

public interface EmployeeService {

	//******CRUD Operations******//
	List<Employee> getAllEmployee();
	Employee getById(Long id);
	String createEmployee(Employee employee);
	String updateEmployee(Employee employee);
	String deleteEmployee(Long id);
	//*******12 Operations*********//
	Double totalSalaryByDepartmentIdEmployee(Long id);
	List<Employee> getEmployeeByDateName(Date joinDate, Date endDate);
	String updatePositionById(Long id);
	Double gatSalaryByPosition(int position);
	Boolean promotionEligibulityById(Long id);
	String getDepartmentRank(Long id);
	List<Employee> getListOfEmployees(int n);
	List<Department> getDepartmentList();
	String sendNotificationToEmployee(String text);
	List<Employee> duplicateEmployee();
	String getDepartmentUpadte();

}
