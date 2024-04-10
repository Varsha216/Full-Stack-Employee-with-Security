package com.varsha.Employeebackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.varsha.Employeebackend.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
