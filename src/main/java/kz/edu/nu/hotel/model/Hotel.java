package kz.edu.nu.hotel.model;

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

    @OneToMany(cascade = CascadeType.ALL)
    private List<Room> rooms = new ArrayList<>();

    protected Hotel() {}

    public Hotel(String name, String address, List<PhoneNumber> phoneNumbers) {
        this.name = name;
        this.address = address;
        this.phoneNumbers = phoneNumbers;//.stream().map(PhoneNumber::new).collect(Collectors.toList());

        List<Room> rooms = new ArrayList<>();
        Random random = new Random();

        int floors = random.nextInt(2, 10);
        int perFloor = random.nextInt(10, 30);

        for (int i = 1; i <= floors; i++) {
            for (int j = 1; j <= perFloor; j++) {
                rooms.add(new Room(RoomType.Single,
                        random.nextInt(30, 50),
                        random.nextInt(2, 6),
                        String.format("%d.%03d", i, j),
                        i));
            }
        }

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
}
