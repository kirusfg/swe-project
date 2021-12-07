package kz.edu.nu.hotel.model.employee;

import kz.edu.nu.hotel.model.User;

import javax.persistence.*;

@Entity
@Table(name = "Employee", schema = "public")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false)
    private EmployeeRole role;

    protected Employee() {}

    public Employee(String email, String name, String surname, EmployeeRole role) {
        this.role = role;
        this.user = new User(email, name, surname);
    }

    public Long getId() {
        return id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return this.user;
    }

    public EmployeeRole getRole() {
        return role;
    }

    public void setRole(EmployeeRole role) {
        this.role = role;
    }
}
