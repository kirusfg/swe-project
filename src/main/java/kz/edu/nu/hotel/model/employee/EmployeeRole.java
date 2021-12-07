package kz.edu.nu.hotel.model.employee;

import javax.persistence.*;

@Entity
@Table(name = "EmployeeRole", schema = "public")
public class EmployeeRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String description;

    protected EmployeeRole() {}

    public EmployeeRole(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
