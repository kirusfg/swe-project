package kz.edu.nu.hotel.controller;

import kz.edu.nu.hotel.model.Employee;
import kz.edu.nu.hotel.repository.EmployeeRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {
    private final EmployeeRepository repository;

    public EmployeeController(EmployeeRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/employees")
    public List<Employee> all() {
        return (List<Employee>) this.repository.findAll();
    }

    @PostMapping("/employees")
    public Employee newEmployee(@RequestBody Employee newEmployee) {
        return this.repository.save(newEmployee);
    }

    @GetMapping("/employees/{id}")
    public Employee byId(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @PutMapping("/employees/{id}")
    public Employee edit(@RequestBody Employee newEmployee, @PathVariable Long id) {
        return repository.findById(id)
                .map(employee -> {
                    employee.setName(newEmployee.getName());
                    employee.setSurname(newEmployee.getSurname());
                    employee.setRole(newEmployee.getRole());
                    return repository.save(employee);
                })
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @DeleteMapping("/employees/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
