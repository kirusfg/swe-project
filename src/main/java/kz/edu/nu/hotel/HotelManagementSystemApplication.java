package kz.edu.nu.hotel;

import kz.edu.nu.hotel.model.Hotel;
import kz.edu.nu.hotel.repository.HotelRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HotelManagementSystemApplication {

    private static final Logger log = LoggerFactory.getLogger(HotelManagementSystemApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(HotelManagementSystemApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(HotelRepository repository) {
        return (args) -> {
            // save a few customers
            repository.save(new Hotel("Venezzia Palace", "Ankara"));
            repository.save(new Hotel("Belek Plaza 5 star", "Istanbul"));

            // fetch all hotels
            log.info("Hotels found with findAll():");
            log.info("-------------------------------");
            for (Hotel hotel : repository.findAll()) {
                log.info(hotel.toString());
            }
            log.info("");
        };
    }
}
