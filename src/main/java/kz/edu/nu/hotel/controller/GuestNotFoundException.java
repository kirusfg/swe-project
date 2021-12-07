package kz.edu.nu.hotel.controller;

public class GuestNotFoundException extends RuntimeException {
    public GuestNotFoundException(String email) {
        super("Could not find a guest with email " + email);
    }
}
