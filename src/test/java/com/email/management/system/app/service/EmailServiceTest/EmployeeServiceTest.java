package com.email.management.system.app.service.EmailServiceTest;

import com.email.management.system.app.model.Employee;
import com.email.management.system.app.repository.EmployeeRepository;
import com.email.management.system.app.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveEmployee() {
        // Arrange
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("John Doe");
        employee.setEmail("john.doe@example.com");
        employee.setDesignation("Software Engineer");
        employee.setCtc(75000.00);

        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        // Act
        Employee savedEmployee = employeeService.saveEmployee(employee);

        // Assert
        assertEquals(employee.getId(), savedEmployee.getId());
        assertEquals(employee.getName(), savedEmployee.getName());
        assertEquals(employee.getEmail(), savedEmployee.getEmail());
        assertEquals(employee.getDesignation(), savedEmployee.getDesignation());
        assertEquals(employee.getCtc(), savedEmployee.getCtc());
        verify(employeeRepository, times(1)).save(any(Employee.class));
    }

    @Test
    void testGetAllEmployees() {
        // Arrange
        Employee employee1 = new Employee(1L, "john.doe@example.com", "John Doe", "Software Engineer", 75000.00);
        Employee employee2 = new Employee(2L, "jane.smith@example.com", "Jane Smith", "QA Engineer", 68000.00);

        List<Employee> employees = Arrays.asList(employee1, employee2);
        when(employeeRepository.findAll()).thenReturn(employees);

        // Act
        List<Employee> result = employeeService.getAllEmployees();

        // Assert
        assertEquals(2, result.size());
        assertEquals(employee1, result.get(0));
        assertEquals(employee2, result.get(1));
        verify(employeeRepository, times(1)).findAll();
    }
}
