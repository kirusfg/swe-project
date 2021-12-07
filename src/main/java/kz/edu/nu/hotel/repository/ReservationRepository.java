package kz.edu.nu.hotel.repository;

import kz.edu.nu.hotel.model.Reservation;
import kz.edu.nu.hotel.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {

    public Optional<Reservation> findByGuestUser(@Param("user") User user);

    Reservation findById(@Param("id") long id);
}
