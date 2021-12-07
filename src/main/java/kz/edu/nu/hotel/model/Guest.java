package kz.edu.nu.hotel.model;

import javax.persistence.*;

@Entity
@Table(name = "Guest", schema = "public")
public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private String idType;

    @Column
    private String idNumber;

    @Column
    private String address;

    @OneToOne(cascade = CascadeType.ALL)
    private PhoneNumber homePhoneNumber;

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    private PhoneNumber mobilePhoneNumber;

    protected Guest() {}

    public Guest(String email, String name, String surname, String mobilePhoneNumber) {
        this.user = new User(email, name, surname);
        this.mobilePhoneNumber = new PhoneNumber(mobilePhoneNumber);
    }

    public Long getId() {
        return id;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String number) {
        this.idNumber = number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public PhoneNumber getHomePhoneNumber() {
        return homePhoneNumber;
    }

    public void setHomePhoneNumber(PhoneNumber homePhoneNumber) {
        this.homePhoneNumber = homePhoneNumber;
    }

    public PhoneNumber getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

    public void setMobilePhoneNumber(PhoneNumber mobilePhoneNumber) {
        this.mobilePhoneNumber = mobilePhoneNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
