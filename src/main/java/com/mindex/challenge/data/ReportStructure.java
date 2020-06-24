package com.mindex.challenge.data;

/**
 * 
 * @author xplin
 * Jun 24, 2020
 */
public class ReportStructure {
	private Employee employee;
	private int numberOfReports;

	public ReportStructure() {
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public int getNumberOfReports() {
		return numberOfReports;
	}

	public void setNumberOfReports(int numberOfReports) {
		this.numberOfReports = numberOfReports;
	}

}
