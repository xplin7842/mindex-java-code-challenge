package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportStructure;
import com.mindex.challenge.service.EmployeeService;
import com.mindex.challenge.service.ReportStructureService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
/**
 * 
 * @author xplin
 * Jun 24, 2020
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReportStructureImplTest {

	private String reportStructureUrl;
	private String employeeUrl;

	@Autowired
	private ReportStructureService reportStructureService;

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;
	private Employee rick, sharon, joe, steve, john;

	@Before
	public void setup() {

		reportStructureUrl = "http://localhost:" + port + "/reportstructure/{id}";
		employeeUrl = "http://localhost:" + port + "/employee";

		// set 2 employees for Joe
		ArrayList<Employee> list = new ArrayList<Employee>();
		rick = new Employee();
		rick.setFirstName("Rick");
		rick.setLastName("Garison");
		rick.setDepartment("Engineering");
		rick.setPosition("Developer");
		sharon = new Employee();
		sharon.setFirstName("Sharon");
		sharon.setLastName("Q");
		sharon.setDepartment("Engineering");
		sharon.setPosition("Developer");
		sharon = restTemplate.postForEntity(employeeUrl, sharon, Employee.class).getBody();

		rick = restTemplate.postForEntity(employeeUrl, rick, Employee.class).getBody();
		list.add(rick);
		list.add(sharon);
		joe = new Employee();
		joe.setFirstName("Joe");
		joe.setLastName("Kong");
		joe.setDepartment("Engineering");
		joe.setPosition("Manager");
		joe.setDirectReports(list);
		joe = restTemplate.postForEntity(employeeUrl, joe, Employee.class).getBody();

		// none reports to steve
		steve = new Employee();
		steve.setFirstName("Steve");
		steve.setLastName("Smith");
		steve.setDepartment("Engineering");
		steve.setPosition("Senior Manager Assitant");
		steve = restTemplate.postForEntity(employeeUrl, steve, Employee.class).getBody();

		john = new Employee();
		john.setFirstName("John");
		john.setLastName("Doe");
		john.setDepartment("Engineering");
		john.setPosition("Senior Manager");

		list = new ArrayList<Employee>();
		list.add(steve);
		list.add(joe);
		john.setDirectReports(list);
		john = restTemplate.postForEntity(employeeUrl, john, Employee.class).getBody();

	}

	@Test
	public void testReportNumber() {

		ReportStructure reportStructure =

				restTemplate.getForEntity(reportStructureUrl, ReportStructure.class, rick.getEmployeeId()).getBody();

		assertNotNull(rick.getEmployeeId());
		assertEquals(reportStructure.getNumberOfReports(), 0);

		reportStructure =

				restTemplate.getForEntity(reportStructureUrl, ReportStructure.class, sharon.getEmployeeId()).getBody();
		assertNotNull(sharon.getEmployeeId());
		assertEquals(reportStructure.getNumberOfReports(), 0);

		reportStructure =

				restTemplate.getForEntity(reportStructureUrl, ReportStructure.class, joe.getEmployeeId()).getBody();
		assertNotNull(joe.getEmployeeId());
		assertEquals(reportStructure.getNumberOfReports(), 2);

		reportStructure =

				restTemplate.getForEntity(reportStructureUrl, ReportStructure.class, steve.getEmployeeId()).getBody();
		assertNotNull(steve.getEmployeeId());
		assertEquals(reportStructure.getNumberOfReports(), 0);

		reportStructure =

				restTemplate.getForEntity(reportStructureUrl, ReportStructure.class, john.getEmployeeId()).getBody();
		assertNotNull(joe.getEmployeeId());
		assertEquals(reportStructure.getNumberOfReports(), 4);

	}

}
