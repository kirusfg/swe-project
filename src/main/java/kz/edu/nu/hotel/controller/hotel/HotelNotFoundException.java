package kz.edu.nu.hotel.controller.hotel;

public class HotelNotFoundException extends RuntimeException {
    public HotelNotFoundException(Long id) {
        super("Could not find hotel with id " + id);
    }
}
