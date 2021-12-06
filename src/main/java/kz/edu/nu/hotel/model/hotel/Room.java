package kz.edu.nu.hotel.model.hotel;

import javax.persistence.*;

enum RoomType {
    Single,
    Double
}

@Entity
@Table(name = "Room", schema = "public")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private RoomType type;

    @Column
    private int size;

    @Column
    private int capacity;

    @Column
    private String roomNumber;

    @Column
    private int floor;

    @ManyToOne
    private Hotel hotel;

    protected Room() {}

    public Room(Long id, RoomType type, int size, int capacity, String roomNumber, int floor) {
        this.id = id;
        this.type = type;
        this.size = size;
        this.capacity = capacity;
        this.roomNumber = roomNumber;
        this.floor = floor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoomType getType() {
        return type;
    }

    public void setType(RoomType type) {
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
