package com.mindex.challenge.data;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

/**
 * 
 * @author xplin
 * Jun 24, 2020
 */

public class Compensation {
	@Id
	private String employeeId;
	private Employee employee;
	private int salary;
	private Date effectiveDate;

	public Compensation() {
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employee) {
		this.employeeId = employee;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int sal) {
		this.salary = sal;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date d) {
		this.effectiveDate = d;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}
