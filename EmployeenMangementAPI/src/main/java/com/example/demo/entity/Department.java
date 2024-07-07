package com.example.demo.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="department")
public class Department {
	
	@Id
	private Long id;
	private String name;
	private Double avg_score;

	
	@OneToMany(mappedBy="department")
	@JsonManagedReference
	List<Employee> employee;
	
	

	public Department() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Department(Long id, String name,Double avg_score, List<Employee> employee) {
		super();
		this.id = id;
		this.name = name;
		this.employee = employee;
		this.avg_score = avg_score;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Employee> getEmployee() {
		return employee;
	}

	public void setEmployee(List<Employee> employee) {
		this.employee = employee;
	}

	public Double getAvg_score() {
		return avg_score;
	}

	public void setAvg_score(Double avg_score) {
		this.avg_score = avg_score;
	}
	
	


}
