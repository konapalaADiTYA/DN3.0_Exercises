package com.example.EmployeeManagementSystem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Derived query method to find employees by department name
    List<Employee> findByDepartmentName(String departmentName);

    // Derived query method to find employees by email
    Employee findByEmail(String email);

    // Custom query using @Query annotation to find employees by name
    @Query("SELECT e FROM Employee e WHERE e.name = :name")
    List<Employee> findEmployeesByName(@Param("name") String name);

    // Custom query using @Query annotation to find departments with more than X employees
    @Query(value = "SELECT d.* FROM departments d WHERE (SELECT COUNT(*) FROM employees e WHERE e.department_id = d.id) > :count", nativeQuery = true)
    List<Employee> findDepartmentsWithMoreThanXEmployees(@Param("count") Long count);

    // Use the named query defined in the Employee entity
    List<Employee> findByDepartmentId(Long departmentId);
}
