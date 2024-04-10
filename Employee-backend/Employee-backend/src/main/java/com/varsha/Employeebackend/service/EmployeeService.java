package com.varsha.Employeebackend.service;

import java.util.List;

import com.varsha.Employeebackend.model.Employee;

public interface EmployeeService {

	Employee addEmployee(Employee employee);
	List<Employee> listEmployee();
	Employee fetchEmployeeById(Long employeeID);
	String deleteEmployee(Long employeeID);
	Employee editEmployee(Long employeeID, Employee employee);
}
