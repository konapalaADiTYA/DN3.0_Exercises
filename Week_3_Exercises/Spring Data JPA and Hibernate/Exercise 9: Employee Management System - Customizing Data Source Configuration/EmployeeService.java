package com.example.EmployeeManagementSystem;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Page<Employee> getAllEmployees(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    public List<Employee> findEmployeesByDepartmentName(String departmentName, Pageable pageable) {
        return employeeRepository.findByDepartmentName(departmentName, pageable);
    }

    public Page<Employee> findEmployeesByName(String name, Pageable pageable) {
        return employeeRepository.findByNameContaining(name, pageable);
    }
    
    public List<EmployeeNameAndDepartmentProjection> getEmployeeNamesAndDepartments() {
        return employeeRepository.findEmployeeNamesAndDepartments();
    }

    // Existing method for class-based projection (if needed)
    public List<EmployeeDetailsDTO> getEmployeeDetailsByName(String name) {
        return employeeRepository.findEmployeeDetailsByName(name);
    }
    
    @Transactional
    public void saveAllEmployees(List<Employee> employees) {
        for (int i = 0; i < employees.size(); i++) {
            employeeRepository.save(employees.get(i));
            if (i % 20 == 0) { // Flush and clear every 20 inserts
                employeeRepository.flush();
                employeeRepository.clear();
            }
        }
    }
}