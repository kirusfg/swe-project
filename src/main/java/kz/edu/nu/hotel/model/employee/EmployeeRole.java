package kz.edu.nu.hotel.model.employee;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
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
