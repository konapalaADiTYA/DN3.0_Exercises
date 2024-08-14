package com.example.EmployeeManagementSystem;

import com.example.EmployeeManagementSystem.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByDepartmentName(String departmentName);

    Employee findByEmail(String email);
}
