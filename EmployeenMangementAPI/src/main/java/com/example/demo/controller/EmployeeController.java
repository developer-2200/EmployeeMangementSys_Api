package com.example.demo.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Department;
import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;

@RestController
@RequestMapping("/emp")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
		
	}
	
	//CRUD Operations in employee table	
	@GetMapping
	public List<Employee> getAllEmployeeDetail() {
		return employeeService.getAllEmployee();
	}

	@GetMapping("/{id}")
	public Employee getEmployeeDetailById(@PathVariable("id") Long id) {
		return employeeService.getById(id);
	}

    @PostMapping
	public String createEmployeeDetail(@RequestBody Employee employee) {
		return  employeeService.createEmployee(employee);
	}
    @PutMapping
    public String updateEmployeeDetailById(@RequestBody Employee employee) {
    	
    	return employeeService.updateEmployee(employee);
    }
    
    //12 operations
    @GetMapping("/total_salary_departmentid/{department_id}")
    public Double totalSalaryDetailByDepartmentId(@PathVariable("department_id") Long id) {
    	return employeeService.totalSalaryByDepartmentIdEmployee(id);
    }
    
    @GetMapping("/list/jd={joindate},ed={enddate}")
    public List<Employee> getEmployeeDetailByDateName(@PathVariable("joindate")Date joinDate,@PathVariable("enddate")Date endDate){
    	return employeeService.getEmployeeByDateName(joinDate, endDate);
    }
   
    @GetMapping("/updateposition/{id}")
    public String getPositionUpdate(@PathVariable("id")Long id) {
    	return employeeService.updatePositionById(id) ;
    	
    }
    @GetMapping("/avgsalary/{position}")
    public Double getAvgSalaryDetailByPosition(@PathVariable("position")int position) {
    	return employeeService.gatSalaryByPosition(position);
    }
    @GetMapping("/promotion/{id}")
    public Boolean promotionEligibulity(@PathVariable("id")Long id) {
    	return employeeService.promotionEligibulityById(id);
    }
    @GetMapping("/deupdate/{id}")
    public String departmentRankUpdate(@PathVariable("id")Long id) {
    	return employeeService.getDepartmentRank(id);
    }
    @GetMapping("/number/{n}")
    public List<Employee> getHighestPaidEmployee(@PathVariable("n")int n){
    	return employeeService.getListOfEmployees(n);    	
    }
    @GetMapping("/department/")
    public List<Department> getDepartmentList(){
    	return employeeService.getDepartmentList() ;
    }
    @GetMapping("/message/{text}")
    public String sendNotification(@PathVariable("text")String text) {
		return employeeService.sendNotificationToEmployee(text);    	
    }
    @GetMapping("/duplicate")
    public List<Employee> duplocatyeEmp(){
    	return employeeService.duplicateEmployee();
    	
    }
    


    
    
	
	
}
