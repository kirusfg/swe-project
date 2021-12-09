package kz.edu.nu.hotel;

import kz.edu.nu.hotel.model.Guest;
import kz.edu.nu.hotel.model.Hotel;
import kz.edu.nu.hotel.model.PhoneNumber;
import kz.edu.nu.hotel.repository.GuestRepository;
import kz.edu.nu.hotel.repository.HotelRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.List;

@SpringBootApplication
@EnableScheduling
public class HotelManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(HotelManagementSystemApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(HotelRepository hotels, GuestRepository guests) throws Exception {
        return args -> {
            Hotel hotel = new Hotel("Luxury Plaza",
                    "Hampshire sq., 22, London",
                    List.of(new PhoneNumber("+12345678"), new PhoneNumber("+87654321")));
            hotels.save(hotel);

            Guest guest = new Guest("guest@mail.com", "Guest", "Guestov", "+11111111");
            guests.save(guest);

            System.out.println("Manager email: " + hotel.getManager().getUser().getEmail());
            System.out.println("Clerk email: " + hotel.getClerk().getUser().getEmail());
            System.out.println("Guest email: guest@mail.com");
        };
    }
}
