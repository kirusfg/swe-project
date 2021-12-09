package kz.edu.nu.hotel.model;

import javax.persistence.*;

@Entity
@Table(name = "Phone_Number", schema = "public")
public class PhoneNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String number;

    protected PhoneNumber() {}

    public PhoneNumber(String number) {
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number){
        this.number = number;
    }
}