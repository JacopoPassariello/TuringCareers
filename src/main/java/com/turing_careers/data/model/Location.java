package com.turing_careers.data.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "Location")
@Getter
@Setter
@NoArgsConstructor
@ToString
@NamedQueries({
    @NamedQuery(name = "findAllLocation", query = "SELECT l FROM Location l"),
    @NamedQuery(name = "findLocationById", query = "SELECT l FROM Location l WHERE l.id = :id"),
    @NamedQuery(name = "findLocationLatAndLog", query = "SELECT l FROM Location l WHERE l.lat = :lat AND l.lon = :lon")
})
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "locationId", nullable = false)
    @JsonProperty("_Location__id")
    private Long id;

    @Column(name = "loc_name", nullable = false)
    @JsonProperty("_Location__name")
    private String name;

    @Column(name = "lat", nullable = false)
    @JsonProperty("_Location__lat")
    private Double lat;

    @Column(name = "lon", nullable = false)
    @JsonProperty("_Location__lon")
    private Double lon;

    @OneToMany(mappedBy = "location")
    private List<Developer> developers;

    public Location(String name, Double lat, Double lon) {
        this.name = name;
        this.lat = lat;
        this.lon = lon;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
