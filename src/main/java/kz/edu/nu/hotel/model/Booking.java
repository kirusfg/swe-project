package kz.edu.nu.hotel.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Booking", schema = "public")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private RoomType type;

    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Guest guest;

    @Column(nullable = false)
    private Date start;

    @Column(nullable = false)
    private Date finish;

    protected Booking() {}

    public Booking(Guest guest, RoomType type, Date start, Date finish) {
        this.guest = guest;
        this.type = type;
        this.start = start;
        this.finish = finish;
    }

    public Long getId() {
        return this.id;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getFinish() {
        return finish;
    }

    public void setFinish(Date finish) {
        this.finish = finish;
    }

    public RoomType getType() {
        return type;
    }

    public void setType(RoomType type) {
        this.type = type;
    }
}