package kz.edu.nu.hotel.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.edu.nu.hotel.model.Guest;
import kz.edu.nu.hotel.model.User;
import kz.edu.nu.hotel.model.employee.Employee;
import kz.edu.nu.hotel.repository.GuestRepository;
import kz.edu.nu.hotel.repository.UserRepository;
import kz.edu.nu.hotel.repository.EmployeeRepository;
import kz.edu.nu.hotel.controller.employee.EmployeeNotFoundException;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Auth")
@RequestMapping("/auth")
@RestController
public class AuthController {
    private final UserRepository users;
    private final GuestRepository guests;
    private final EmployeeRepository employees;

    public AuthController(UserRepository users, GuestRepository guests, EmployeeRepository employees) {
        this.users = users;
        this.guests = guests;
        this.employees = employees;
    }

    @Operation(summary = "Logs in a guest with a given email")
    @PostMapping("/login")
    public Guest guestLogin(@RequestBody String email) {
        final User user = users.findByEmail(email);
        return guests.findByUser(user).orElseThrow(() -> new GuestNotFoundException(email));
    }

    @Operation(summary = "Logs in an employee with a given email")
    @PostMapping("/employee/login")
    public Employee employeeLogin(@RequestBody String email) {
        final User user = users.findByEmail(email);
        return employees.findByUser(user).orElseThrow(() -> new EmployeeNotFoundException(email));
    }

    @Operation(summary = "Registers a new guest")
    @PostMapping("/register")
    public Guest register(@RequestBody Guest guest) {
        return guests.save(guest);
    }
}
