package kz.edu.nu.hotel.repository.hotel;

import kz.edu.nu.hotel.model.hotel.Hotel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HotelRepository extends CrudRepository<Hotel, Long> {

    List<Hotel> findByName(String name);

    Hotel findById(@Param("id") long id);
}
