package kz.edu.nu.hotel.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Schedule", schema = "public")
public class ScheduleEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee cleaner;

    @OneToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @Column
    private Date cleanTime;

    protected ScheduleEntry() {}

    public ScheduleEntry(Employee cleaner, Room room, Date when) {
        this.cleaner = cleaner;
        this.room = room;
        this.cleanTime = when;
    }

    public Long getId() {
        return this.id;
    }

    public Room getRoom() {
        return this.room;
    }

    public Employee getCleaner() {
        return this.cleaner;
    }

    public Date getCleanTime() {
        return this.cleanTime;
    }
}
