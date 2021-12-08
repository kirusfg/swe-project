package kz.edu.nu.hotel.controller.employee;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.edu.nu.hotel.model.Employee;
import kz.edu.nu.hotel.repository.EmployeeRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Employees")
@RestController
public class EmployeeController {
    private final EmployeeRepository repository;

    public EmployeeController(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Operation(summary = "Returns all employees")
    @GetMapping("/employees")
    public List<Employee> all() {
        return (List<Employee>) this.repository.findAll();
    }

    @Operation(summary = "Adds a new employee")
    @PostMapping("/employees")
    public Employee newEmployee(@RequestBody Employee newEmployee) {
        return this.repository.save(newEmployee);
    }

    @Operation(summary = "Finds an employee by id")
    @GetMapping("/employees/{id}")
    public Employee byId(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @Operation(summary = "Modifies an employee by id")
    @PutMapping("/employees/{id}")
    public Employee edit(@RequestBody Employee newEmployee, @PathVariable Long id) {
        return repository.findById(id)
                .map(employee -> {
                    employee.setUser(newEmployee.getUser());
                    employee.setRole(newEmployee.getRole());
                    return repository.save(employee);
                })
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @Operation(summary = "Deletes an employee with a given id")
    @DeleteMapping("/employees/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
