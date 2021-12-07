package kz.edu.nu.hotel.controller.employee;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(Long id) {
        super("Could not find employee with id " + id);
    }

    public EmployeeNotFoundException(String email) {
        super("Could not find employee with email " + email);
    }
}
