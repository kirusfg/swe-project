package kz.edu.nu.hotel.model;

import javax.persistence.*;

@Entity
@Table(name = "Feature", schema = "public")
public class Feature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String description;


    protected Feature() {}

    public Feature(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}