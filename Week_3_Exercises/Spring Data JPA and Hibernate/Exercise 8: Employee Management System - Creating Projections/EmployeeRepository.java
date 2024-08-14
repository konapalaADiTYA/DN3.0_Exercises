package com.example.EmployeeManagementSystem;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByDepartmentName(String departmentName, Pageable pageable);

    Employee findByEmail(String email);

    @Query("SELECT e FROM Employee e WHERE e.name = :name")
    List<Employee> findEmployeesByName(@Param("name") String name);

    @Query(value = "SELECT d.* FROM departments d WHERE (SELECT COUNT(*) FROM employees e WHERE e.department_id = d.id) > :count", nativeQuery = true)
    List<Employee> findDepartmentsWithMoreThanXEmployees(@Param("count") Long count);

    List<Employee> findByDepartmentId(Long departmentId);
    
    @Query("SELECT e.name AS name, d.name AS departmentName FROM Employee e JOIN e.department d")
    List<EmployeeNameAndDepartmentProjection> findEmployeeNamesAndDepartments();
    
    @Query("SELECT new com.example.EmployeeManagementSystem.projection.EmployeeDetailsDTO(e.name, e.email, d.name) " +
            "FROM Employee e JOIN e.department d WHERE e.name LIKE %:name%")
     List<EmployeeDetailsDTO> findEmployeeDetailsByName(@Param("name") String name);

	Page<Employee> findByNameContaining(String name, Pageable pageable);
}
