package com.ems.employee_management_system.service;

import com.ems.employee_management_system.entity.Employee;
import com.ems.employee_management_system.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
    }

    // Business Logic: Update an existing employee
    public Employee updateEmployee(Long id, Employee employeeDetails) {
        Employee existingEmployee = getEmployeeById(id);

        existingEmployee.setName(employeeDetails.getName());
        existingEmployee.setEmail(employeeDetails.getEmail());

        // Update relationships if provided in the request
        if (employeeDetails.getDepartment() != null) {
            existingEmployee.setDepartment(employeeDetails.getDepartment());
        }
        if (employeeDetails.getProject() != null) {
            existingEmployee.setProject(employeeDetails.getProject());
        }

        return employeeRepository.save(existingEmployee);
    }

    // Business Logic: Delete an employee
    public void deleteEmployee(Long id) {
        Employee existingEmployee = getEmployeeById(id);
        employeeRepository.delete(existingEmployee);
    }
}