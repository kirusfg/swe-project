package kz.edu.nu.hotel.repository;

import kz.edu.nu.hotel.model.Booking;
import kz.edu.nu.hotel.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BookingRepository extends CrudRepository<Booking, Long> {

    public Optional<Booking> findByGuestUser(@Param("user") User user);

    Booking findById(@Param("id") long id);
}