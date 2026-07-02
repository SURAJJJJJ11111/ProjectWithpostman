package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.example.demo.exception.InvalidDesignationException;
import com.example.demo.exception.EmployeeNotFoundException;
import com.example.demo.entity.Employee;
import com.example.demo.exception.EmployeeNotFoundException;
import com.example.demo.exception.InvalidDesignationException;
import com.example.demo.repository.EmployeeRepository;

@Service
public class EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public Employee save(Employee emp) {

        applyDesignationSalary(emp);

        return repository.save(emp);
    }

    public List<Employee> getAll() {
        return repository.findAll();
    }

    public Employee getById(Integer id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new EmployeeNotFoundException(
                                "Employee not found with id : " + id));
    }

    public Employee raiseSalary(Integer id, double amount) {

        Employee emp = getById(id);

        emp.setSalary(emp.getSalary() + amount);

        return repository.save(emp);
    }

    public void delete(Integer id) {

        Employee emp = getById(id);

        repository.delete(emp);
    }

    private void applyDesignationSalary(Employee emp) {

        switch (emp.getDesignation().trim().toLowerCase()) {

        case "programmer":
            emp.setSalary(25000);
            break;

        case "manager":
            emp.setSalary(30000);
            break;

        case "tester":
            emp.setSalary(20000);
            break;

        default:
            throw new InvalidDesignationException(
                    "Designation must be Programmer, Manager or Tester");
        }
    }
}