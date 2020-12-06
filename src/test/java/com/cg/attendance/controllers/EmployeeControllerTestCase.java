package com.cg.attendance.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.cg.attendance.entities.AttendanceDetail;
import com.cg.attendance.entities.Employee;
import com.cg.attendance.services.EmployeeService;

@SpringBootTest
public class EmployeeControllerTestCase {

	@InjectMocks
	EmployeeController employeeController;

	@Mock
	EmployeeService service;

		
	@Test
	public void getEmployeeByEmpIdTest() {
		
		Employee employee = new Employee("46045170", "Suparna Arya", "7980064539", "suparna.arya@capgemini.com",
				"Bangalore", "46045157", null);
		
		when(service.viewEmployeeByEmpId(any(String.class))).thenReturn(employee);
		
		ResponseEntity<?> responseEntity = employeeController.getEmployeeByEmpId("46045170");
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);

	}
	
	
	@Test
	public void getAttendanceByEmpIdTest() {
		
		Employee employee = new Employee("46045170", "Suparna Arya", "7980064539", "suparna.arya@capgemini.com",
				"Bangalore", "46045157", null);
		
		AttendanceDetail attendance1 = new AttendanceDetail("10:10","14:30",null, "","","APPLIED", employee);
		AttendanceDetail attendance2 = new AttendanceDetail("10:10","14:30",null, "","","APPLIED", employee);
		List<AttendanceDetail> attendanceList = new ArrayList<AttendanceDetail>();
		attendanceList.add(attendance1);
		attendanceList.add(attendance2);
		
		
		when(service.viewAttendanceByEmpId(any(String.class))).thenReturn(attendanceList);
		
		ResponseEntity<?> responseEntity = employeeController.getAttendanceByEmpId("46045170");
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);

	}
	
	
	@Test
	public void getEmployeeUnderSupervisiorTest() {
		
		Employee employee1 = new Employee("46045170", "Suparna Arya", "7980064539", "suparna.arya@capgemini.com",
				"Bangalore", "46045157", null);
		
		Employee employee2 = new Employee("46045171", "Suparna Arya", "7980064539", "suparna.arya@capgemini.com",
				"Bangalore", "46045157", null);
		
		List<Employee> empList = new ArrayList<Employee>();
		
		empList.add(employee1);
		empList.add(employee2);
		
		when(service.viewEmployeesUnderSupervisior(any(String.class))).thenReturn(empList);
		
		ResponseEntity<?> responseEntity = employeeController.getEmployeeUnderSupervisior("7980064539");
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);

	}


}
