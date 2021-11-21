package kz.edu.nu.hotel.controller;

import kz.edu.nu.hotel.model.Hotel;
import kz.edu.nu.hotel.repository.HotelRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HotelController {
    private final HotelRepository repository;

    public HotelController(HotelRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/hotels")
    public List<Hotel> all() {
        return (List<Hotel>) repository.findAll();
    }

    @PostMapping("/hotels")
    public Hotel newHotel(@RequestBody Hotel newHotel) {
        return repository.save(newHotel);
    }

    @GetMapping("/hotels/{id}")
    public Hotel byId(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new HotelNotFoundException(id));
    }

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

    @DeleteMapping("/hotels/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}