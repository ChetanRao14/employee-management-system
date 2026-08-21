package com.ems.employee_management_system.service;

import com.ems.employee_management_system.entity.Department;
import com.ems.employee_management_system.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    // Constructor Injection (Best Practice for Inversion of Control)
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    // Business Logic: Save a new department
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    // Business Logic: Retrieve all departments
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    // Business Logic: Find a department by its ID
    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found with id: " + id));
    }
}