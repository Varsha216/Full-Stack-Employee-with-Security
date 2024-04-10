package com.varsha.Employeebackend.service;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import com.varsha.Employeebackend.model.Employee;
import com.varsha.Employeebackend.repository.EmployeeRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class EmployeeServiceImplTest {

	@InjectMocks
	private EmployeeServiceImpl empService;
	
	@Mock
	private EmployeeRepository empRepository;
	
	private Employee employee;
	
	
	@BeforeEach
	public void setup(){
		employee = new Employee(22L, "Varsha", "9999988888", "Pune", "varsha45@gmail.com");
		
	}
	
	@Test
	public void testAddEmployee() {
		when(empRepository.save(any(Employee.class))).thenReturn(employee);
		assertEquals("Varsha", empService.addEmployee(employee).getEmployeeName());
	}
	
	@Test
	public void testListEmployee() {
		List<Employee> listEmp = new ArrayList<>();
		listEmp.add(new Employee(22L, "Varsha", "9999988888", "Mumbai", "varsha45@gmail.com"));
		listEmp.add(new Employee(23L, "Annie", "7777788888", "Mumbai", "annie@gmail.com"));
		
		when(empRepository.findAll()).thenReturn(listEmp);
		assertEquals(2, empService.listEmployee().size());
	}
	
	@Test
	public void testFetchEmployeeById() {
		when(empRepository.findById(22L)).thenReturn(Optional.of(employee));
		assertEquals("Varsha", empService.fetchEmployeeById(22L).getEmployeeName());
	}
	
	@Test
	public void testDeleteEmployee() {
		Long empId = 22L;
		doReturn(Optional.of(employee)).when(empRepository).findById(empId);
		doNothing().when(empRepository).delete(employee);
		empService.deleteEmployee(empId);
		verify(empRepository, times(1)).delete(employee);
	}
	
	@Test
	public void testUpdateEmployee() {
		Long empId = 22L;
		when(empRepository.findById(empId)).thenReturn(Optional.of(employee));
		when(empRepository.save(any(Employee.class))).thenReturn(employee);
		assertEquals("Varsha",empService.editEmployee(empId, employee).getEmployeeName());
	}
}
