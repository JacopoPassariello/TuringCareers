package com.turing_careers.data.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "Developer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@NamedQueries({
    @NamedQuery(name = "findAllDevelopers", query = "SELECT d FROM Developer d"),
    @NamedQuery(name = "findDeveloperByMail", query = "SELECT d FROM Developer d WHERE d.mail = :mail"),
    @NamedQuery(name = "findDevsByMailAndPassword", query = "SELECT d FROM Developer d WHERE d.mail = :mail  AND d.password = :password")
})
public class Developer implements User, Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "developerId", nullable = false)
    private Long id;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Column(name = "bio", nullable = false)
    private String bio;

    @Column(name = "mail", nullable = false)
    private String mail;

    @Column(name = "passwordAccount", nullable = false)
    private String password;

    @OneToOne
    @JoinColumn(name = "locationId")
    private Location location;

    @ManyToMany
    @JoinTable(name = "DeveloperSkill")
    private List<Skill> skills;

    @ManyToMany
    @JoinTable(name = "DeveloperLanguage")
    private List<Language> languages;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "DeveloperOffer",
            joinColumns = @JoinColumn(name = "offerId"),
            inverseJoinColumns = @JoinColumn(name = "developerId")
    )
    private List<Offer> savedOffers;

    public Developer(String firstName, String lastName, String bio, String mail, String password, Location location, List<Skill> skills, List<Language> languages, List<Offer> savedOffers) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.bio = bio;
        this.mail = mail;
        this.password = password;
        this.location = location;
        this.skills = skills;
        this.languages = languages;
        this.savedOffers = savedOffers;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
