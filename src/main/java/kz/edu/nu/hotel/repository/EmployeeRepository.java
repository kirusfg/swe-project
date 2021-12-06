package kz.edu.nu.hotel.repository;

import kz.edu.nu.hotel.model.employee.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    List<Employee> findByName(String name);

    Employee findById(@Param("id") long id);
}