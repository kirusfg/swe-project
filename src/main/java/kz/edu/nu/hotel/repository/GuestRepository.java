package kz.edu.nu.hotel.repository;

import kz.edu.nu.hotel.model.Guest;
import kz.edu.nu.hotel.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface GuestRepository extends CrudRepository<Guest, Long> {
    public Optional<Guest> findByUser(@Param("user") User user);
}
