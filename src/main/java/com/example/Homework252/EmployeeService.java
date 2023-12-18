package com.example.Homework252;

import java.util.ArrayList;

import java.util.List;

public class EmployeeService {
    private List<Employee> employees;
    private static final int MAX_EMPLOYEES = 10;

    public EmployeeService() {
        this.employees = new ArrayList<>();
    }

    public void addEmployee(String firstName, String lastName) throws EmployeeStorageIsFullException, EmployeeAlreadyAddedException {
        if (employees.size() >= MAX_EMPLOYEES) {
            throw new EmployeeStorageIsFullException("Employee storage is full");
        }

        Employee newEmployee = new Employee(firstName, lastName);
        if (employees.contains(newEmployee)) {
            throw new EmployeeAlreadyAddedException("Employee already added");
        }

        employees.add(newEmployee);
    }

    public Employee removeEmployee(String firstName, String lastName) {
        Employee employeeToRemove = new Employee(firstName, lastName);
        if (!employees.remove(employeeToRemove)) {
            try {
                throw new EmployeeNotFoundException("Employee not found");
            } catch (EmployeeNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return employeeToRemove;
    }

    public Employee findEmployee(String firstName, String lastName) {
        Employee employeeToFind = new Employee(firstName, lastName);
        int index = employees.indexOf(employeeToFind);
        if (index == -1) {
            try {
                throw new EmployeeNotFoundException("Employee not found");
            } catch (EmployeeNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return employees.get(index);
    }

    public List<Employee> getAllEmployees() {
        return employees;
    }

    private class EmployeeNotFoundException extends Throwable {
        public EmployeeNotFoundException(String employeeNotFound) {
        }
    }

    class EmployeeStorageIsFullException extends Throwable {
        public EmployeeStorageIsFullException(String employeeStorageIsFull) {
        }
    }

    class EmployeeAlreadyAddedException extends Throwable {
        public EmployeeAlreadyAddedException(String employeeAlreadyAdded) {
        }
    }
}