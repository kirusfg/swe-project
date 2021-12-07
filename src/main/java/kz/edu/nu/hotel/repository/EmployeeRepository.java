package kz.edu.nu.hotel.repository;

import kz.edu.nu.hotel.model.employee.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    Employee findByUserEmail(String email);

    Employee findById(@Param("id") long id);
}