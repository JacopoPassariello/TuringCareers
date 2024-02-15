package com.turing_careers.data.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jdk.jfr.Name;
import lombok.*;

import java.util.List;

/**
 * Modella l'entità persistente Offer
 */
@Entity
@Table(name = "Offer")
@Getter
@Setter
@NoArgsConstructor
@ToString
@NamedQueries({
    @NamedQuery(name = "findAllOffers", query = "SELECT o FROM Offer o"),
    @NamedQuery(name = "findOfferById", query = "SELECT o FROM Offer o WHERE o.id = :id"),
    @NamedQuery(name = "searchOffer", query = "SELECT o FROM Offer o WHERE o.title LIKE '%' || :query || '%' OR o.description LIKE '%' || :query || '%'")
})
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Offer implements Item {

    public static final String STATE_OPEN = "OPEN";
    public static final String STATE_PAUSED = "PAUSED";
    public static final String STATE_CLOSED = "CLOSED";
    public static final String ON_SITE = "ON_SITE";
    public static final String REMOTE = "REMOTE";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "offerId", nullable = false)
    @JsonProperty("_Offer__id")
    private Long id;

    @Column(name = "title", nullable = false)
    @JsonProperty("_Offer__title")
    private String title;

    @Column(name = "offerDescription", nullable = false)
    @JsonProperty("_Offer__description")
    private String description;

    @Column(name = "state", nullable = false)
    @JsonProperty("_Offer__state")
    private String state;

    @Column(name = "locationType", nullable = false)
    @JsonProperty("_Offer__location_type")
    private String locationType;

    @Column(name = "locationName")
    @JsonProperty("_Offer__location")
    private String location;

    @ManyToOne
    @JoinColumn(name = "employerId")
    @JsonProperty("_Offer__employer")
    @JsonBackReference("employerOffers")
    private Employer employer;

    @ManyToMany
    @JoinTable(
            name = "OfferSkill",
            joinColumns = @JoinColumn(name = "offerId"),
            inverseJoinColumns = @JoinColumn(name = "skillId")
    )
    @JsonProperty("_Offer__skills")
    @ToString.Exclude
    // @JsonManagedReference("offerSkills")
    private List<Skill> skills;

    @ManyToMany
    @JoinTable(
            name = "OfferLanguage",
            joinColumns = @JoinColumn(name = "offerId"),
            inverseJoinColumns = @JoinColumn(name = "languageId")
    )
    @JsonProperty("_Offer__languages")
    @ToString.Exclude
    // @JsonManagedReference("languageOffer")
    private List<Language> languages;

    @ManyToMany(mappedBy = "savedOffers", fetch = FetchType.LAZY)
    @ToString.Exclude
    // @JsonManagedReference("developerOffers")
    private List<Developer> subscribedDevelopers;


    public Offer(String title, String description, String state, String locationType, Employer employer, String location, List<Skill> skills, List<Language> languages) {
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
