package kz.edu.nu.hotel.model.hotel;

import kz.edu.nu.hotel.model.PhoneNumber;

import javax.persistence.*;

@Entity
@Table(name = "Guest", schema = "public")
public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String idType;

    @Column
    private String idNumber;

    @Column
    private String address;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "home_phone_number_id", referencedColumnName = "id", nullable = false)
    private PhoneNumber homePhoneNumber;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "mobile_phone_number_id", referencedColumnName = "id", nullable = false)
    private PhoneNumber mobilePhoneNumber;

    protected Guest() {}

    public Guest(String idType, String idNumber, String address, String homePhoneNumber, String mobilePhoneNumber) {
        this.idType = idType;
        this.idNumber = idNumber;
        this.address = address;
        this.homePhoneNumber = new PhoneNumber(homePhoneNumber);
        this.mobilePhoneNumber = new PhoneNumber(mobilePhoneNumber);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getNumber() {
        return idNumber;
    }

    public void setNumber(String idNumber) {
        this.idNumber = idNumber;
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
}
