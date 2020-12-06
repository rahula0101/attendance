package com.cg.attendance.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.attendance.entities.AttendanceDetail;
import com.cg.attendance.entities.Employee;
import com.cg.attendance.exception.EmployeeIDException;
import com.cg.attendance.exception.SupervisiorIDException;
import com.cg.attendance.repositories.EmployeeRepository;

@Service
public class EmployeeService implements IEmployeeService {
	@Autowired
	private EmployeeRepository empRepo;

	@Override
	public Employee viewEmployeeByEmpId(String empId) {
		Employee emp = empRepo.findByEmpId(empId);
		if (emp == null) {
			throw new EmployeeIDException("No employee with such id " + empId);
		}
		return emp;
	}

	@Override
	public Employee addEmployee(Employee employee) {
		
		try {
			if (employee.getEmpId().equals(employee.getSupervisiorId())) {
				throw new SupervisiorIDException("Employee can't be its own Supervisior");
			}
			return empRepo.save(employee);
		} catch (SupervisiorIDException e) {
			throw new SupervisiorIDException("Employee can't be its own Supervisior");
		} catch (Exception e) {
			throw new EmployeeIDException("Employee id " + employee.getEmpId() + " already available");
		}
	}

	@Override
	public List<Employee> viewEmployeesUnderSupervisior(String supervisiorId) {
		List<Employee> employee = empRepo.findEmployeesUnderSupervisior(supervisiorId);
		if (employee == null) {
			throw new SupervisiorIDException("No employee works under supervisior whose id is " + supervisiorId);
		}
		return employee;
	}

	@Override
	public List<AttendanceDetail> viewAttendanceByEmpId(String empId) {
		Employee emp = viewEmployeeByEmpId(empId);
		System.out.println(emp.getEmpId());
		if (emp == null) {
			throw new EmployeeIDException("No attendance with employee id as " + empId + " exists");
		}
		 
		  
		  
		return emp.getAttendance();
	}
}