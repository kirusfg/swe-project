package kz.edu.nu.hotel.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.edu.nu.hotel.model.Guest;
import kz.edu.nu.hotel.model.User;
import kz.edu.nu.hotel.model.Employee;
import kz.edu.nu.hotel.repository.GuestRepository;
import kz.edu.nu.hotel.repository.UserRepository;
import kz.edu.nu.hotel.repository.EmployeeRepository;
import kz.edu.nu.hotel.controller.employee.EmployeeNotFoundException;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Guest")
@RestController
public class GuestController {
    private final GuestRepository guests;

    public GuestController(GuestRepository guests) {
        this.guests = guests;
    }

    @Operation(summary = "Modifies an guest by id")
    @PutMapping("/guest/{id}")
    public Guest edit(@RequestBody Guest newGuest, @PathVariable Long id) {
        return guests.findById(id)
                .map(guest -> {
                    guest.setAddress(newGuest.getAddress());
                    if(guest.getHomePhoneNumber() == null){
                        guest.setHomePhoneNumber(newGuest.getHomePhoneNumber());
                    }else{
                        guest.getHomePhoneNumber().setNumber(newGuest.getHomePhoneNumber().getNumber());
                    }
                    guest.setIdNumber(newGuest.getIdNumber());
                    guest.setIdType(newGuest.getIdType());
                    return guests.save(guest);
                })
                .orElseThrow(() -> new GuestNotFoundException(id));
    }
}