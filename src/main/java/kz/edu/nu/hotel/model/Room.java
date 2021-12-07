package kz.edu.nu.hotel.model;

import javax.persistence.*;

@Entity
@Table(name = "Room", schema = "public")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String type;

    @Column
    private int size;

    @Column
    private int capacity;

    @Column(name = "room_number")
    private String roomNumber;

    @Column
    private int floor;

    /*
        base price for room (changes according to week of the day)
     */
    @Column
    private int price;

    @ManyToOne
    private Hotel hotel;

    @ManyToOne
    private Guest guest;

    public Room() {
    }

    public Room(Long id, String type, int size, int capacity, String roomNumber, int floor, int price) {
        this.id = id;
        this.type = type;
        this.size = size;
        this.capacity = capacity;
        this.roomNumber = roomNumber;
        this.floor = floor;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

}
