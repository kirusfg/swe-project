package kz.edu.nu.hotel.model;

import javax.persistence.*;

@Entity
@Table(name = "bill", schema = "public")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int price;

    @ManyToOne
    private Guest guest;

    @ManyToOne
    private Hotel hotel;
}
