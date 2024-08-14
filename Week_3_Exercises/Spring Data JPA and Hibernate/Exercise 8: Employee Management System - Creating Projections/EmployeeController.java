package com.example.EmployeeManagementSystem;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public Page<Employee> getAllEmployees(Pageable pageable) {
        return employeeService.getAllEmployees(pageable);
    }

    @GetMapping("/by-department")
    public List<Employee> findEmployeesByDepartmentName(@RequestParam String departmentName, Pageable pageable) {
        return employeeService.findEmployeesByDepartmentName(departmentName, pageable);
    }
    
    @GetMapping("/names-and-departments")
    public List<EmployeeNameAndDepartmentProjection> getEmployeeNamesAndDepartments() {
        return employeeService.getEmployeeNamesAndDepartments();
    }
    
    @GetMapping("/details")
    public List<EmployeeDetailsDTO> getEmployeeDetailsByName(@RequestParam String name) {
        return employeeService.getEmployeeDetailsByName(name);
    }
    @GetMapping("/by-name")
    public Page<Employee> findEmployeesByName(@RequestParam String name, Pageable pageable) {
        return employeeService.findEmployeesByName(name, pageable);
    }

    @GetMapping("/sorted")
    public Page<Employee> getSortedEmployees(
            @RequestParam String sortBy,
            @RequestParam(defaultValue = "asc") String direction,
            Pageable pageable) {
        
        Sort sort = direction.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
        return employeeService.getAllEmployees(pageable);
        
        
    }
}
