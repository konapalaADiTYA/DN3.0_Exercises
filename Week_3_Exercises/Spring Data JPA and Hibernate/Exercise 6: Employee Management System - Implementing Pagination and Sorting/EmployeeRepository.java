package com.example.EmployeeManagementSystem;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Page<Employee> findAll(Pageable pageable);

    Page<Employee> findByDepartmentName(String departmentName, Pageable pageable);

    @Query("SELECT e FROM Employee e WHERE e.name LIKE %:name%")
    Page<Employee> findByNameContaining(@Param("name") String name, Pageable pageable);
}
