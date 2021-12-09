package kz.edu.nu.hotel.controller;

public class ReservationNotFoundException extends RuntimeException {
    public ReservationNotFoundException(Long id) {
        super("Could not find a reservation with id " + id);
    }
}
