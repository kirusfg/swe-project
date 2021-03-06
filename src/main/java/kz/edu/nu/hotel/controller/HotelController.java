package kz.edu.nu.hotel.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.edu.nu.hotel.model.Hotel;
import kz.edu.nu.hotel.model.Reservation;
import kz.edu.nu.hotel.model.Booking;
import kz.edu.nu.hotel.model.Room;
import kz.edu.nu.hotel.model.ScheduleEntry;
import kz.edu.nu.hotel.repository.HotelRepository;
import kz.edu.nu.hotel.repository.ReservationRepository;
import kz.edu.nu.hotel.repository.BookingRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Hotels")
@RestController
public class HotelController {
    private final HotelRepository hotels;
    private final ReservationRepository reservations;
    private final BookingRepository bookings;

    public HotelController(HotelRepository hotels, ReservationRepository reservations, BookingRepository bookings) {
        this.hotels = hotels;
        this.reservations = reservations;
        this.bookings = bookings;
    }

    @Operation(summary = "Returns all hotels")
    @GetMapping("/hotels")
    public List<Hotel> all() {
        return (List<Hotel>) hotels.findAll();
    }

    @Operation(summary = "Creates a new hotel")
    @PostMapping("/hotels")
    public Hotel newHotel(@RequestBody Hotel newHotel) {
        return hotels.save(new Hotel(newHotel.getName(), newHotel.getAddress(), newHotel.getPhoneNumbers()));
    }

    @Operation(summary = "Finds a hotel by id")
    @GetMapping("/hotels/{id}")
    public Hotel byId(@PathVariable Long id) {
        return hotels.findById(id).orElseThrow(() -> new HotelNotFoundException(id));
    }

    @Operation(summary = "Returns all rooms of a hotel by id")
    @GetMapping("/hotels/{id}/rooms")
    public List<Room> roomsById(@PathVariable Long id) {
        Hotel hotel = hotels.findById(id)
                .orElseThrow(() -> new HotelNotFoundException(id));

        return hotel.getRooms();
    }

    @Operation(summary = "Creates a reservation for a room in a hotel by id")
    @PostMapping("/hotels/{id}/reserve")
    public Reservation reserve(@PathVariable Long id,
                               @RequestBody Reservation newReservation) {
        Hotel hotel = hotels.findById(id)
                .orElseThrow(() -> new HotelNotFoundException(id));

        hotel.newReservation(newReservation);

        return reservations.save(newReservation);
    }

    @Operation(summary = "Lists all reservations for rooms in a hotel by id")
    @GetMapping("/hotels/{id}/reservations")
    public List<Reservation> reservations(@PathVariable Long id) {
        Hotel hotel = hotels.findById(id)
                .orElseThrow(() -> new HotelNotFoundException(id));

        return hotel.getReservations();
    }

    @Operation(summary = "Gets a cleaning schedule of a hotel by id")
    @GetMapping("/hotels/{id}/schedule")
    public List<ScheduleEntry> scheduleEntries(@PathVariable Long id) {
        Hotel hotel = hotels.findById(id)
                .orElseThrow(() -> new HotelNotFoundException(id));

        return hotel.getScheduleEntries();
    }

    @Operation(summary = "Modifies a hotel by id")
    @PutMapping("/hotels/{id}")
    public Hotel edit(@RequestBody Hotel newHotel, @PathVariable Long id) {
        return hotels.findById(id)
                .map(hotel -> {
                    hotel.setName(newHotel.getName());
                    hotel.setAddress(newHotel.getAddress());
                    return hotels.save(hotel);
                })
                .orElseThrow(() -> new HotelNotFoundException(id));
    }

    @Operation(summary = "Deletes a hotel with a given id")
    @DeleteMapping("/hotels/{id}")
    public void delete(@PathVariable Long id) {
        hotels.deleteById(id);
    }
  
    @Operation(summary = "Turns the reservation to the booking")
    @PostMapping("/hotels/{hotelId}/book/{reservationId}")
    public Booking booking(@PathVariable Long hotelId, @PathVariable Long reservationId, @RequestBody Room room) {
        Hotel hotel = hotels.findById(hotelId)
                .orElseThrow(() -> new HotelNotFoundException(hotelId));

        Reservation reservation = reservations.findById(reservationId)
                .orElseThrow(() -> new ReservationNotFoundException(room.getId()));

        Booking newBooking = new Booking(reservation.getGuest(), reservation.getType(), reservation.getStart(), reservation.getFinish());

        Room actualRoom = hotel.getRooms().stream()
                        .filter(r -> r.getRoomNumber().equals(room.getRoomNumber()))
                                .collect(Collectors.toList()).get(0);
        actualRoom.occupy();
        hotel.newBooking(newBooking);

        hotel.deleteReservation(reservation);
        reservations.delete(reservation);

        return bookings.save(newBooking);
    }
}
