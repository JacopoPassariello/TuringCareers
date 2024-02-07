package com.turing_careers.data.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.id.factory.spi.GenerationTypeStrategy;

import java.util.List;

@Entity
@Table(name = "Offer")
@Getter
@Setter
@NoArgsConstructor
@ToString
@NamedQueries({
    @NamedQuery(name = "findAllOffers", query = "SELECT o FROM Offer o"),
    @NamedQuery(name = "findOfferById", query = "SELECT o FROM Offer o WHERE o.id = :id")
})
public class Offer implements Item{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "offerId", nullable = false)
    private Long id;

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

    public Offer(String title, String description, String state, String locationType, Employer employer, Location location, List<Skill> skills, List<Language> languages) {
        this.title = title;
        this.description = description;
        this.state = state;
        this.locationType = locationType;
        this.employer = employer;
        this.location = location;
        this.skills = skills;
        this.languages = languages;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
