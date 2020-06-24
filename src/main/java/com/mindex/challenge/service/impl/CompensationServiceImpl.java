package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.CompensationService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

/**
 * 
 * @author xplin
 * Jun 24, 2020
 */
@Service
public class CompensationServiceImpl implements CompensationService {

	private static final Logger LOG = LoggerFactory.getLogger(CompensationServiceImpl.class);

	@Autowired
	private CompensationRepository compensationRepository;
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public Compensation create(Compensation Compensation) {
		LOG.debug("Creating Compensation [{}]", Compensation);

		Employee employee = Compensation.getEmployee();
		if (employee != null) {
			String empId = employee.getEmployeeId();
			if (empId != null) {
				// check if the the employ exist
				Employee e = employeeRepository.findByEmployeeId(empId);
				if (e != null) {

					Compensation.setEmployeeId(empId);
					try {
						compensationRepository.insert(Compensation);
						LOG.info("Compensation Record inserted.........[" + Compensation.getEmployeeId() + ","
								+ Compensation.getSalary() + "," + Compensation.getEffectiveDate());
					} catch (RuntimeException e1) {
						LOG.error("Error:" + e1.getMessage());
						throw e1;
					}
				} else {
					LOG.error("Employ ID " + empId + " cannot be found. Compensation record not created");
					throw new RuntimeException(
							"Employ ID " + empId + " cannot be found. Compensation record not created");
				}
			} else {
				LOG.error("Invalid Employee ID. Compensation record not created.");
				throw new RuntimeException("Invalid Employee ID. Compensation record not created.");
			}
		}
		return Compensation;
	}

	@Override
	public Compensation read(String id) {
		LOG.debug("Read Compensation with id [{}]", id);
		Compensation Compensation = compensationRepository.findByEmployeeId(id);
		Employee employee = this.employeeRepository.findByEmployeeId(id);
		if (Compensation == null || employee == null) {
			LOG.error("Invalid EmployeeId: " + id);
			throw new RuntimeException("Invalid EmployeeId: " + id);
		}
		Compensation.setEmployee(employee);
		return Compensation;
	}

}
