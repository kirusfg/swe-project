package kz.edu.nu.hotel.model.hotel;

import kz.edu.nu.hotel.model.PhoneNumber;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "Hotel", schema = "public")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 300)
    private String address;

    @OneToMany(cascade = CascadeType.ALL)
    private List<PhoneNumber> phoneNumbers = new ArrayList<>();

    protected Hotel() {}

    public Hotel(String name, String address, List<String> phoneNumbers) {
        this.name = name;
        this.address = address;
        this.phoneNumbers = phoneNumbers.stream().map(PhoneNumber::new).collect(Collectors.toList());
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
