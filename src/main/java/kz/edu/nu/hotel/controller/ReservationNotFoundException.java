<<<<<<< HEAD
package kz.edu.nu.hotel.controller;

public class ReservationNotFoundException extends RuntimeException {
    public ReservationNotFoundException(Long id) {
        super("Could not find reservation with id " + id);
    }
}
=======
package kz.edu.nu.hotel.controller;

public class ReservationNotFoundException extends RuntimeException {
    public ReservationNotFoundException(Long id) {
        super("Could not find a reservation with id " + id);
    }
}
>>>>>>> e9d816fa6cdd092384add736bb28c1886ff594b3
