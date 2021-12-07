package kz.edu.nu.hotel.model;

import kz.edu.nu.hotel.model.employee.Employee;
import kz.edu.nu.hotel.model.employee.EmployeeRole;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Employee> employees = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reservation> reservations = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
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

        List<Employee> employees = new ArrayList<>();
        Employee manager = new Employee(String.format("manager%d@mail.com", this.id),
                "Admin",
                "Employev",
                new EmployeeRole("manager"));
        employees.add(manager);

        for (int i = 0; i < random.nextInt(50); i++) {
            Employee worker = new Employee(String.format("worker%d@mail.com", i),
                    "Employee",
                    "Employev",
                    new EmployeeRole("worker"));
            employees.add(worker);
        }

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
}
