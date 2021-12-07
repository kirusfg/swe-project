package kz.edu.nu.hotel.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.edu.nu.hotel.model.Hotel;
import kz.edu.nu.hotel.model.Room;
import kz.edu.nu.hotel.repository.HotelRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Hotels")
@RestController
public class HotelController {
    private final HotelRepository repository;

    public HotelController(HotelRepository repository) {
        this.repository = repository;
    }

    @Operation(summary = "Returns all hotels")
    @GetMapping("/hotels")
    public List<Hotel> all() {
        return (List<Hotel>) repository.findAll();
    }

    @Operation(summary = "Creates a new hotel")
    @PostMapping("/hotels")
    public Hotel newHotel(@RequestBody Hotel newHotel) {
        return repository.save(new Hotel(newHotel.getName(), newHotel.getAddress(), newHotel.getPhoneNumbers()));
    }

    @Operation(summary = "Finds a hotel by id")
    @GetMapping("/hotels/{id}")
    public Hotel byId(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new HotelNotFoundException(id));
    }

    @Operation(summary = "Returns all rooms of a hotel by id")
    @GetMapping("/hotels/{id}/rooms")
    public List<Room> roomsById(@PathVariable Long id) {
        Hotel hotel = repository.findById(id)
                .orElseThrow(() -> new HotelNotFoundException(id));

        return hotel.getRooms();
    }

    @Operation(summary = "Modifies a hotel by id")
    @PutMapping("/hotels/{id}")
    public Hotel edit(@RequestBody Hotel newHotel, @PathVariable Long id) {
        return repository.findById(id)
                .map(hotel -> {
                    hotel.setName(newHotel.getName());
                    hotel.setAddress(newHotel.getAddress());
                    return repository.save(hotel);
                })
                .orElseThrow(() -> new HotelNotFoundException(id));
    }

    @Operation(summary = "Deletes a hotel with a given id")
    @DeleteMapping("/hotels/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}