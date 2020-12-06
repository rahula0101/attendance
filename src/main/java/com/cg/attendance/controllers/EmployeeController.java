package com.cg.attendance.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.attendance.entities.Employee;
import com.cg.attendance.services.EmployeeService;
import com.cg.attendance.services.MapValidationErrorService;

@RestController
public class EmployeeController {
	@Autowired
	private EmployeeService empService;
	@Autowired
	private MapValidationErrorService mapValidateErrorService;

	@PostMapping("/employee/add")
	public ResponseEntity<?> addNewEmployee(@Valid @RequestBody Employee employee, BindingResult result) {
		ResponseEntity<?> errorMap = mapValidateErrorService.mapValidationError(result);
		if (errorMap != null)
			return errorMap;
		Employee newEmployee = empService.addEmployee(employee);

		return new ResponseEntity<Employee>(newEmployee, HttpStatus.CREATED);

	}

	@GetMapping
	@RequestMapping("/employee/{empId}")
	public ResponseEntity<?> getEmployeeByEmpId(@PathVariable String empId) {
		return new ResponseEntity<Employee>(empService.viewEmployeeByEmpId(empId), HttpStatus.OK);
	}

	@GetMapping
	@RequestMapping("/supervisior/{supervisiorId}")
	public ResponseEntity<List<Employee>> getEmployeeUnderSupervisior(@PathVariable String supervisiorId) {
		return new ResponseEntity<List<Employee>>(empService.viewEmployeesUnderSupervisior(supervisiorId),
				HttpStatus.OK);
	}

}