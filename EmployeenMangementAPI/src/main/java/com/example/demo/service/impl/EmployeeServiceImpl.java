package com.example.demo.service.impl;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Department;
import com.example.demo.entity.Employee;
import com.example.demo.entity.Name;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.NameRepository;
import com.example.demo.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private NameRepository nameRepository;
	@Autowired
	private DepartmentRepository departmentRepository;
	
	
	public EmployeeServiceImpl(EmployeeRepository employeeRepository, NameRepository nameRepository,
			DepartmentRepository departmentRepository) {
		super();
		this.employeeRepository = employeeRepository;
		this.nameRepository = nameRepository;
		this.departmentRepository = departmentRepository;
	}

//******CRUD Operations*******************************************************************************
	
	public List<Employee> getAllEmployee() {
		
		return employeeRepository.findAll();
	}


	
	public Employee getById(Long id) {
		 return employeeRepository.findById(id).get();
	}


	
	public String createEmployee(Employee employee) {
		employeeRepository.save(employee);
		return "Created";
	}


	
	public String updateEmployee(Employee employee) {
		employeeRepository.save(employee);
		return "updated";
	}

	
	public String deleteEmployee(Long id) {
		employeeRepository.deleteById(id);
		return "deleted";
		
	}
//*******(12  Operations)************************************************************ 

	@Override
	public Double totalSalaryByDepartmentIdEmployee(Long id) {
		return employeeRepository.getSalaryByDepartmentId(id);
	}

	@Override
	public List<Employee> getEmployeeByDateName(Date joinDate, Date endDate) {
		List<String> names = nameRepository.gatNmaesOfJoiningDate(joinDate,endDate);
		
		return employeeRepository.getEmployeeByNames(names);
		
	}

	@Override
	public String updatePositionById(Long id) {
		Employee employee = employeeRepository.findById(id).get();
		Name name = employee.getName();
		Date date= name.getJoinDate();
		String result=nameRepository.getResultDate(date);
		
		if(result=="NO") {
      	  return  "Be Passionate, All the Best";        	           	   
         }else {
      	  
      	   int post = employee.getPost();
      	    post = post + 1;
      	    
      	    employee.setPost(post);
      	   employeeRepository.save(employee);
      	   return"Congrats Post Updated";
         }     		
	}

	@Override
	public Double gatSalaryByPosition(int position) {
		// TODO Auto-generated method stub
		return employeeRepository.getAverageSalary(position);
	}

	@Override
	public Boolean promotionEligibulityById(Long id) {
		Employee emp = employeeRepository.findById(id).get();
		Name n = emp.getName();
		Date date = n.getJoinDate();
		String result = nameRepository.getResultDate(date);

		// score form name
		Double score = n.getScore();
		// score is out of 5 and result experience less than 5 years
		if (result == "NO" && score < 4.0) {
			return false;
		} else {
			Employee employee = employeeRepository.findById(id).get();
			int post = employee.getPost();
			post = post + 1;

			employee.setPost(post);
			employeeRepository.save(employee);
			return true;
		}
	}

	@Override
	public String getDepartmentRank(Long id) {
		// Average salary of all employees working in department
		Double avg_salary = employeeRepository.getAverageSalaryByDepartmentId(id);
		
		
		// Average score from name Table
		List<String> names = employeeRepository.getEmployeesByDepartmentId(id);
		Double score = nameRepository.getAverageScore(names);
		
		
		// if average salary is less than 29000 in department
		// OR average score is more than or equals to 4
		// then only we should update budget
		if (avg_salary <= 29000.0 || score >= 4.0) {
			return "Update the Budget";
		} else {
			return "No Need ";
		}
				
	}

	@Override
	public List<Employee> getListOfEmployees(int n) {
		// TODO Auto-generated method stub
		return employeeRepository.getEmployeeInOrder(n);
	}

	
	@Override
	public List<Department> getDepartmentList() {
		

		return departmentRepository.getDepartmentList();

	}

	@Override
	public String sendNotificationToEmployee(String text) {
		List<String> name = nameRepository.findAllNames();
		nameRepository.sendNotification(text,name);
		return "Notified";
	}

	@Override
	public List<Employee> duplicateEmployee() {
		List<String> emails= nameRepository.getDuplicateEmails();
		List<String> names = nameRepository.getNameByEmailID(emails);
		
		return employeeRepository.getEmployeeByNames(names);
	}

	@Override
	public String getDepartmentUpadte() {
		
		Long x = departmentRepository.count();


		for (  Long y = 0L; y <= x; y++) {

			List<String> names = employeeRepository.getEmployeesByDepartmentId(y);
			Double ascore = nameRepository.getAverageScore(names);
		  departmentRepository.updateWithScore(ascore, y);
		}
		
		return "updated";
	}	
	
	
	

}
