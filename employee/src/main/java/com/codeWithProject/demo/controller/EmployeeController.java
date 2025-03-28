package com.codeWithProject.demo.controller;

import com.codeWithProject.demo.entity.Employee;
import com.codeWithProject.demo.repository.EmployeeRepository;
import com.codeWithProject.demo.service.EmployeeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api1")
@RequiredArgsConstructor
@CrossOrigin("*")
public class EmployeeController {
    private final EmployeeService employeeService;
    private final EmployeeRepository employeeRepository;

    @PostMapping("/employee")
    public Employee postEmployee(@RequestBody Employee employee) {
        return employeeService.postEmployee(employee);
    }

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        try {
            employeeService.deleteEmployees(id);
            return new ResponseEntity<>("Employee with id " + id + " deleted successfully!", HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable Long id) {
        Employee employee = employeeService.getEmployeeById(id);
        if (employee == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(employee);
    }
    	
    @PatchMapping("/employee/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        Employee updatedEmployee = employeeService.updateEmployee(id, employee);

        if (updatedEmployee == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return ResponseEntity.ok(updatedEmployee);
    }
}
