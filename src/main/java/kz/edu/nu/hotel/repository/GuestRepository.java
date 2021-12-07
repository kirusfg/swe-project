package kz.edu.nu.hotel.repository;

import kz.edu.nu.hotel.model.Guest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GuestRepository extends CrudRepository<Guest, Long> {

    List<Guest> findByIdNumber(String idNumber);

    Guest findById(@Param("id") long id);
}
