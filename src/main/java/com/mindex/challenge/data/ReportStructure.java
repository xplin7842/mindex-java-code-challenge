package com.mindex.challenge.data;

public class ReportStructure {
    private Employee employee;
    private int numberOfReports;
    public ReportStructure () {
    }

    /*
    public ReportStructure getReportStructure(String employeeId)                 
    {
    	ReportStructure reportStr = null;
    	
        return reportStr;
    }
    */

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
