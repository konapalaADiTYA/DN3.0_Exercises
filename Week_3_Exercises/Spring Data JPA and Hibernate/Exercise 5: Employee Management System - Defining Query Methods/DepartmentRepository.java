package com.example.EmployeeManagementSystem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    // Derived query method to find a department by name
    Optional<Department> findByName(String name);

    // Custom query using @Query annotation to find all departments
    @Query("SELECT d FROM Department d")
    List<Department> findAllDepartments();

    // Custom query using @Query annotation to find a department by id
    @Query("SELECT d FROM Department d WHERE d.id = :id")
    Optional<Department> findDepartmentById(@Param("id") Long id);
}
