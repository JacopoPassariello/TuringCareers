package com.turing_careers.data.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Location")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@NamedQueries({
        @NamedQuery(name = "findAllLocation", query = "SELECT l FROM Location l"),
        @NamedQuery(name = "findLocationLatAndLog", query = "SELECT l FROM Location l WHERE l.lat = :lat AND l.log = :log")
})
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "locationId", nullable = false)
    private int id;

    @Column(name = "loc_name", nullable = false)
    private String name;

    @Column(name = "lat", nullable = false)
    private String lat;

    @Column(name = "log", nullable = false)
    private String log;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
