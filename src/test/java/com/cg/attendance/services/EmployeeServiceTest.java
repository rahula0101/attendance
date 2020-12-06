package com.cg.attendance.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.attendance.entities.Employee;
import com.cg.attendance.exception.SupervisiorIDException;
import com.cg.attendance.repositories.EmployeeRepository;

@SpringBootTest
public class EmployeeServiceTest {

	@InjectMocks
	EmployeeService service;

	@Mock
	private EmployeeRepository empRepo;

	@Test
	public void viewEmployeeByEmpIdTest() {

		Employee employee = new Employee("46045170", "Suparna Arya", "7980064539", "suparna.arya@capgemini.com",
				"Bangalore", "46045157", null);
		when(empRepo.findByEmpId(any(String.class))).thenReturn(employee);

		Employee fetchedObj = service.viewEmployeeByEmpId("46045157");

		assertEquals(fetchedObj.getEmpEmail(), employee.getEmpEmail());

	}

	@Test
	public void addEmployeeTest1() {

		Employee employee = new Employee("46045170", "Suparna Arya", "7980064539", "suparna.arya@capgemini.com",
				"Bangalore", "46045170", null);
		when(empRepo.save(any(Employee.class))).thenReturn(employee);

		assertThrows(SupervisiorIDException.class, () -> service.addEmployee(employee));
	}

	@Test
	public void viewEmployeesUnderSupervisiorTest1() {

		when(empRepo.findEmployeesUnderSupervisior(any(String.class))).thenReturn(null);

		assertThrows(SupervisiorIDException.class, () -> service.viewEmployeesUnderSupervisior("1233"));
	}
	
	
	@Test
	public void viewEmployeesUnderSupervisiorTest2() {
		
		Employee employee1 = new Employee("46045170", "Suparna Arya", "7980064539", "suparna.arya@capgemini.com",
				"Bangalore", "46045157", null);
		
		Employee employee2 = new Employee("46045171", "Suparna Arya", "7980064539", "suparna.arya@capgemini.com",
				"Bangalore", "46045157", null);
		
		List<Employee> employeeList = new ArrayList<Employee>();
		employeeList.add(employee1);
		employeeList.add(employee2);
		

		when(empRepo.findEmployeesUnderSupervisior(any(String.class))).thenReturn(employeeList);
		
		List<Employee> fetchedList = service.viewEmployeesUnderSupervisior("7980064539");

		assertEquals(fetchedList.size(), 2);
	}
	
}
