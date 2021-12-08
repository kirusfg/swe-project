package kz.edu.nu.hotel;

import kz.edu.nu.hotel.model.Employee;
import kz.edu.nu.hotel.model.Hotel;
import kz.edu.nu.hotel.model.Room;
import kz.edu.nu.hotel.model.ScheduleEntry;
import kz.edu.nu.hotel.repository.HotelRepository;
import kz.edu.nu.hotel.repository.ScheduleEntryRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class Cleaning {
    private final HotelRepository hotels;
    private final ScheduleEntryRepository scheduleEntries;

    public Cleaning(HotelRepository hotels, ScheduleEntryRepository scheduleEntries) {
        this.hotels = hotels;
        this.scheduleEntries = scheduleEntries;
    }

    @Scheduled(cron = "*/10 * * * * *")
    public void main() {
        for (Hotel hotel : hotels.findAll()) {
            List<Employee> cleaners = hotel.getCleaners();
            List<Room> rooms = hotel.getRooms();

            int cleanersSize = cleaners.size();
            int roomsSize = rooms.size();

            for (int i = 0; i < roomsSize; i++) {
                Date when = new Date();

                ScheduleEntry entry = new ScheduleEntry(cleaners.get(i % cleanersSize), rooms.get(i), when);

                hotel.addScheduleEntry(entry);
                scheduleEntries.save(entry);
            }
        }
    }
}
