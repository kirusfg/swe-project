package kz.edu.nu.hotel.repository;

import java.util.List;

import kz.edu.nu.hotel.model.Hotel;
import org.springframework.data.repository.CrudRepository;

public interface HotelRepository extends CrudRepository<Hotel, Long> {

    List<Hotel> findByName(String name);

    Hotel findById(long id);
}
