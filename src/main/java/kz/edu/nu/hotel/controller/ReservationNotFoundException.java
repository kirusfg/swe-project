package kz.edu.nu.hotel.controller;

public class ReservationNotFoundException extends RuntimeException {
    public ReservationNotFoundException(Long id) {
        super("Could not find reservation with id " + id);
    }
}