package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.ReportStructure;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.ReportStructureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportStructureServiceImpl implements ReportStructureService {

    private static final Logger LOG = LoggerFactory.getLogger(ReportStructureServiceImpl.class);

    @Autowired
    private EmployeeRepository EmployeeRepository;


    @Override
    public ReportStructure read(String id) {
        LOG.debug("Read ReportStructure with id [{}]", id);

        Employee employee = EmployeeRepository.findByEmployeeId(id);
     
        int numOfReports = getReportNumberById(id);
        
        ReportStructure reportStructure = new ReportStructure();
        reportStructure.setEmployee(employee);	
        reportStructure.setNumberOfReports(numOfReports);
        
        return reportStructure;
    }

    private int getReportNumberById(String id) {
        LOG.debug("Read ReportStructure with id [{}]", id);
        int numOfReports = 0;
        Employee employee = EmployeeRepository.findByEmployeeId(id);
        if (employee != null) 
        {
        	if(employee.getDirectReports() != null)
        	{
		        numOfReports =  employee.getDirectReports().size();
		        if(numOfReports > 0)
		        {
		        	
		        	for (Employee e :  employee.getDirectReports()) {
		        		numOfReports += getReportNumberById(e.getEmployeeId());
		        	}
		        }
        	}
        }
        return numOfReports;
    }
}
