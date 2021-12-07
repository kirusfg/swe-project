package kz.edu.nu.hotel.repository;

import kz.edu.nu.hotel.model.Hotel;
import kz.edu.nu.hotel.model.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoomRepository extends CrudRepository<Room, Long> {

    List<Room> findByRoomNumberAndHotel(String roomNumber, Hotel hotel);

    Room findById(@Param("id") long id);
}
