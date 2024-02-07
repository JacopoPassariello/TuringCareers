package com.turing_careers.data.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.id.factory.spi.GenerationTypeStrategy;

import java.util.List;

@Entity
@Table(name = "Offer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@NamedQueries({
    @NamedQuery(name = "findAllOffers", query = "SELECT o FROM Offer o"),
    @NamedQuery(name = "findOfferById", query = "SELECT o FROM Offer o WHERE o.id = :id")
})
public class Offer implements Item{


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "offerId", nullable = false)
    private int id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "offerDescription", nullable = false)
    private String description;

    @Column(name = "state", nullable = false)
    private String state;

    @Column(name = "locationType", nullable = false)
    private String locationType;

    @ManyToOne
    @JoinColumn(name = "employerId")
    private Employer employer;

    @OneToOne
    @JoinColumn(name = "locationId")
    private Location location;

    @ManyToMany
    @JoinTable(name = "OfferSkill")
    private List<Skill> skills;

    @ManyToMany
    @JoinTable(name = "OfferLanguage")
    private List<Language> languages;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
