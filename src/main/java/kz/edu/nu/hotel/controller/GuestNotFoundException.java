package kz.edu.nu.hotel.controller;

public class GuestNotFoundException extends RuntimeException {
    public GuestNotFoundException(Long id) { super("Couldn't find guest with id " + id); }
}
