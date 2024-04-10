package com.varsha.Employeebackend.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.varsha.Employeebackend.exception.ValidationException;
import com.varsha.Employeebackend.model.Employee;
import com.varsha.Employeebackend.service.AuthenticationService;
import com.varsha.Employeebackend.service.EmployeeService;

@RestController
@CrossOrigin(origins = "*")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private AuthenticationService authService;
	
	@GetMapping("/test")
	public String test() {
		return "Success";
	}

	@PostMapping("/employee")
	public Employee addEmployee(@RequestHeader("Authorization") String jwt, @RequestBody Employee employee) throws ValidationException {
		Employee emp = null;
		
		if(jwt.length() > 0 && authService.validateJwt(jwt))
			emp = employeeService.addEmployee(employee);
		else
			throw new ValidationException("Jwt token is not valid");
		
		return emp;
	}

	@GetMapping("/employee")
	public List<Employee> listEmployee(@RequestHeader("Authorization") String jwt) throws ValidationException {
		List<Employee> employees = new ArrayList<>();
		
		if(jwt.length() > 0 && authService.validateJwt(jwt))
			employees = employeeService.listEmployee();
		else
			throw new ValidationException("Jwt token is not valid");
		
		return employees;
	}

	@GetMapping("/employee/{id}")
	public Employee fetchEmployeeById(@RequestHeader("Authorization") String jwt, @PathVariable("id") Long employeeID) throws ValidationException {
		Employee emp = null;
		
		if(jwt.length() > 0 && authService.validateJwt(jwt))
			emp = employeeService.fetchEmployeeById(employeeID);
		else
			throw new ValidationException("Jwt token is not valid");
		
		return emp;
	}

	@DeleteMapping("/employee/{id}")
	public String deleteEmployee(@RequestHeader("Authorization") String jwt, @PathVariable("id") Long employeeID) throws ValidationException {
		String response = null;
		
		if(jwt.length() > 0 && authService.validateJwt(jwt))
			response = employeeService.deleteEmployee(employeeID);
		else
			throw new ValidationException("Jwt token is not valid");
		
		return response;
	}

	@PutMapping("/employee/{id}")
	public Employee editEmployee(@RequestHeader("Authorization") String jwt, @PathVariable("id") Long employeeID, @RequestBody Employee employee) throws ValidationException {
		Employee emp = null;
		
		if(jwt.length() > 0 && authService.validateJwt(jwt))
			emp = employeeService.editEmployee(employeeID, employee);
		else
			throw new ValidationException("Jwt token is not valid");
		
		
		return emp;
	}
}
