package com.example.demo.entity;



import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name="name")
public class Name {
	
	@Id
	@NotEmpty(message="name should not be empty")
	private String name;

	private Date joinDate;
	
	@Email
    @NotEmpty(message = "Email must not be empty")
	private String email;

	private double score;
	
	private String notification;

	@OneToOne(mappedBy = "name")
    @JsonBackReference
	private Employee employee;

	public Name(@NotEmpty(message = "name should not be empty") String name, Date joinDate,@Email @NotEmpty(message = "Email must not be empty") String email, double score, String notification,
			Employee employee) {
		super();
		this.name = name;
		this.joinDate = joinDate;
		this.email = email;
		this.score = score;
		this.notification = notification;
		this.employee = employee;
	}

	public Name() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public String getNotification() {
		return notification;
	}

	public void setNotification(String notification) {
		this.notification = notification;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	

}
