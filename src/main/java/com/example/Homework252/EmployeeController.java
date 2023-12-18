package com.example.Homework252;

import com.example.Homework252.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/add")
    public Employee addEmployee(@RequestParam String firstName, @RequestParam String lastName) throws EmployeeService.EmployeeAlreadyAddedException, EmployeeService.EmployeeStorageIsFullException {
        employeeService.addEmployee(firstName, lastName);
        return new Employee(firstName, lastName);
    }

    @PostMapping("/remove")
    public Employee removeEmployee(@RequestParam String firstName, @RequestParam String lastName) {
        return employeeService.removeEmployee(firstName, lastName);
    }

    @GetMapping("/find")
    public Employee findEmployee(@RequestParam String firstName, @RequestParam String lastName) {
        return employeeService.findEmployee(firstName, lastName);
    }

    @GetMapping("/all")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleEmployeeNotFoundException(EmployeeNotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(EmployeeStorageIsFullException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleEmployeeStorageIsFullException(EmployeeStorageIsFullException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(EmployeeAlreadyAddedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleEmployeeAlreadyAddedException(EmployeeAlreadyAddedException ex) {
        return ex.getMessage();
    }

    private class EmployeeNotFoundException extends Throwable {
    }

    private class EmployeeStorageIsFullException extends Throwable {
    }

    private class EmployeeAlreadyAddedException extends Throwable {
    }
}