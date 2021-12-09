package kz.edu.nu.hotel.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Entity
@Table(name = "Hotel", schema = "public")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 300)
    private String address;

    @OneToMany(cascade = CascadeType.ALL)
    private List<PhoneNumber> phoneNumbers = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Guest> guests = new ArrayList<>();

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Employee> employees = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reservation> reservations = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Booking> bookings = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<ScheduleEntry> scheduleEntries = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Room> rooms = new ArrayList<>();

    protected Hotel() {}

    public Hotel(String name, String address, List<PhoneNumber> phoneNumbers) {
        this.name = name;
        this.address = address;
        this.phoneNumbers = phoneNumbers;

        Random random = new Random();

        List<Room> rooms = new ArrayList<>();
        int floors = random.nextInt(2, 10);
        int perFloor = random.nextInt(10, 30);

        // N single rooms
        for (int i = 1; i <= floors; i++) {
            for (int j = 1; j <= perFloor; j++) {
                rooms.add(new Room(RoomType.Single,
                        random.nextInt(30, 50),
                        random.nextInt(2, 6),
                        String.format("%d.%03d", i, j),
                        i));
            }
        }

        // N/2 double rooms
        for (int i = 1; i <= floors; i++) {
            for (int j = 1; j <= perFloor / 2; j++) {
                rooms.add(new Room(RoomType.Double,
                        random.nextInt(60, 120),
                        random.nextInt(4, 8),
                        String.format("%d.%03d", i, j),
                        i));
            }
        }

        this.rooms = rooms;

        int index = random.nextInt(1000000);

        List<Employee> employees = new ArrayList<>();
        Employee manager = new Employee(this, String.format("manager%06d@mail.com", index),
                "Admin",
                "Employev",
                EmployeeRole.Manager);
        employees.add(manager);

        for (int i = 0; i < random.nextInt(50); i++) {
            Employee cleaner = new Employee(this, String.format("worker%06d%d@mail.com", index, i),
                    "Cleaner",
                    "Cleanerbekuly",
                    EmployeeRole.Cleaner);
            employees.add(cleaner);
        }

        Employee clerk = new Employee(this, String.format("clerk%06d@mail.com", index),
                "Clerk",
                "Clerkovich",
                EmployeeRole.Clerk);
        employees.add(clerk);

        this.employees = employees;
    }

    public Long getId() {
        return id;
    }

    public List<PhoneNumber> getPhoneNumbers() {
        return phoneNumbers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void newReservation(Reservation reservation) {
        this.reservations.add(reservation);
    }

    public List<Reservation> getReservations() {
        return this.reservations;
    }

    public List<Employee> getCleaners() {
        return this.employees.stream().filter((Employee employee) ->
                employee.getRole().equals(EmployeeRole.Cleaner)).collect(Collectors.toList());
    }

    public List<ScheduleEntry> getScheduleEntries() {
        return scheduleEntries;
    }

    public void addScheduleEntry(ScheduleEntry entry) {
        this.scheduleEntries.add(entry);
    }

    public void newBooking(Booking entry) {
        this.bookings.add(entry);
    }

    public void deleteReservation(Reservation entry) {
        this.bookings.remove(entry);
    }
    
}
