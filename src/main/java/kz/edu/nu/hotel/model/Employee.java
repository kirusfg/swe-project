package kz.edu.nu.hotel.model;

import javax.persistence.*;

@Entity
@Table(name = "Employee", schema = "public")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30)
    private String name;

    @Column(nullable = false, length = 30)
    private String surname;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false)
    private EmployeeRole role;

    protected Employee() {}

    public Employee(String name, String surname, EmployeeRole role) {
        this.name = name;
        this.surname = surname;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public EmployeeRole getRole() {
        return role;
    }

    public void setRole(EmployeeRole role) {
        this.role = role;
    }
}
