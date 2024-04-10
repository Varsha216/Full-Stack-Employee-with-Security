package com.varsha.Employeebackend.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.varsha.Employeebackend.model.Employee;
import com.varsha.Employeebackend.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public Employee addEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	public List<Employee> listEmployee() {
		return employeeRepository.findAll();
	}

	public Employee fetchEmployeeById(Long employeeID) {
		return employeeRepository.findById(employeeID).get();
	}

	public String deleteEmployee(Long employeeID) {
		Employee e = employeeRepository.findById(employeeID).get();
		employeeRepository.delete(e);
		return "Employee Record Deleted";
	}

	public Employee editEmployee(Long employeeID, Employee employee) {
		Employee e = employeeRepository.findById(employeeID).get();
		if (Objects.nonNull(employee.getEmployeeName()) && !"".equals(employee.getEmployeeName())) {
			e.setEmployeeName(employee.getEmployeeName());
		}
		if (Objects.nonNull(employee.getEmployeeEmail()) && !"".equals(employee.getEmployeeEmail())) {
			e.setEmployeeEmail(employee.getEmployeeEmail());
		}
		if (Objects.nonNull(employee.getEmployeePhone()) && !"".equals(employee.getEmployeePhone())) {
			e.setEmployeePhone(employee.getEmployeePhone());
		}
		if (Objects.nonNull(employee.getEmployeLocation()) && !"".equals(employee.getEmployeLocation())) {
			e.setEmployeLocation(employee.getEmployeLocation());
		}

		return employeeRepository.save(e);
	}
}
