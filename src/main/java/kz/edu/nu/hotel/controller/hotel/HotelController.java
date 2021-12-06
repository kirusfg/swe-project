package kz.edu.nu.hotel.controller.hotel;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.edu.nu.hotel.model.hotel.Hotel;
import kz.edu.nu.hotel.repository.hotel.HotelRepository;
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
        return repository.save(newHotel);
    }

    @Operation(summary = "Finds a hotel by id")
    @GetMapping("/hotels/{id}")
    public Hotel byId(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new HotelNotFoundException(id));
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