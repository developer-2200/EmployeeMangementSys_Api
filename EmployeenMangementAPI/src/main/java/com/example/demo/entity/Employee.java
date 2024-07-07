package com.example.demo.entity;




import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="employee")
public class Employee {

	@Id
	private Long id;
	
	private int post;

	private double salary;
	//Form Name.namme as string   and mapped as name  with string of name table
	//JSon manager is given to maintain json format
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="name",referencedColumnName="name")
	@JsonManagedReference
	private Name name;
	
	//Form department.id  and mapped name as department with id of department table
	//Json manager is given to maintain json format
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="department",referencedColumnName="id")
	@JsonBackReference
	private Department department;

	
	

	public Employee(Long id, int post, double salary, Name name,Department department) {
		super();
		this.id = id;
		this.post = post;
		this.salary = salary;
		this.name = name;
		this.department=department;
	}

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getPost() {
		return post;
	}

	public void setPost(int post) {
		this.post = post;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	
	


}
