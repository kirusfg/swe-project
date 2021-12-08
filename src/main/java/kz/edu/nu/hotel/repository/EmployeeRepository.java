package kz.edu.nu.hotel.repository;

import kz.edu.nu.hotel.model.User;
import kz.edu.nu.hotel.model.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    public Optional<Employee> findByUser(@Param("user") User user);

    Employee findById(@Param("id") long id);
}
