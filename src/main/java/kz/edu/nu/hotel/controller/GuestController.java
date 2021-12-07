package kz.edu.nu.hotel.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.edu.nu.hotel.model.Guest;
import kz.edu.nu.hotel.model.Hotel;
import kz.edu.nu.hotel.repository.GuestRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Guests")
@RestController
public class GuestController {
    private final GuestRepository guestRepository;

    public GuestController(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    @Operation(summary = "Finds all guests")
    @GetMapping("/guests")
    public List<Guest> all(){ return (List<Guest>) guestRepository.findAll(); }

    @Operation(summary = "Creates a new guest")
    @PostMapping("/guests")
    public Guest newHotel(@RequestBody Guest newGuest) {
        return guestRepository.save(newGuest);
    }

    @Operation(summary = "Finds a hotel by id")
    @GetMapping("/guests/{id}")
    public Guest byId(@PathVariable Long id) {
        return guestRepository.findById(id).orElseThrow(() -> new GuestNotFoundException(id));
    }

    @Operation(summary = "Modifies a hotel by id")
    @PutMapping("/guests/{id}")
    public Guest edit(@RequestBody Guest newGuest, @PathVariable Long id) {
        return guestRepository.findById(id)
                .map(guest -> {
                    guest.setIdType(newGuest.getIdType());
                    guest.setIdNumber(newGuest.getIdNumber());
                    guest.setAddress(newGuest.getAddress());
                    guest.setHomePhoneNumber(newGuest.getHomePhoneNumber());
                    guest.setMobilePhoneNumber(newGuest.getMobilePhoneNumber());
                    guest.setCheckIn(newGuest.getCheckIn());
                    guest.setCheckOut(newGuest.getCheckOut());
                    return guestRepository.save(guest);
                })
                .orElseThrow(() -> new GuestNotFoundException(id));
    }

    @Operation(summary = "Deletes a hotel with a given id")
    @DeleteMapping("/guests/{id}")
    public void delete(@PathVariable Long id) {
        guestRepository.deleteById(id);
    }
}
