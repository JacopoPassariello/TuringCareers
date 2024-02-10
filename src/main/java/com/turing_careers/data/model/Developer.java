package com.turing_careers.data.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jdk.jfr.Name;
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
    @NamedQuery(name = "findDevsByMailAndPassword", query = "SELECT d FROM Developer d WHERE d.mail = :mail  AND d.password = :password"),
    @NamedQuery(name = "findDeveloperById", query = "SELECT d FROM Developer d WHERE d.id = :id")
})
public class Developer implements User, Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "developerId", nullable = false)
    @JsonProperty("_Developer__id")
    private Long id;

    @Column(name = "firstName", nullable = false)
    @JsonProperty("_Developer__f_name")
    private String firstName;

    @Column(name = "lastName", nullable = false)
    @JsonProperty("_Developer__l_name")
    private String lastName;

    @Column(name = "bio", nullable = false)
    @JsonProperty("_Developer__bio")
    private String bio;

    @Column(name = "mail", nullable = false)
    @JsonProperty("_Developer__mail")
    private String mail;

    @Column(name = "passwordAccount", nullable = false)
    @JsonProperty("_Developer__psw")
    private String password;

    @OneToOne
    @JoinColumn(name = "locationId")
    @JsonProperty("_Developer__location")
    private Location location;

    @ManyToMany
    @JoinTable(name = "DeveloperSkill")
    @JsonProperty("_Developer__skills")
    private List<Skill> skills;

    @ManyToMany
    @JoinTable(name = "DeveloperLanguage")
    @JsonProperty("_Developer__languages")
    private List<Language> languages;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "DeveloperOffer",
            joinColumns = @JoinColumn(name = "offerId"),
            inverseJoinColumns = @JoinColumn(name = "developerId")
    )
    private List<Offer> savedOffers;

    public Developer(String firstName, String lastName, String bio, String mail, String password,
                     Location location, List<Skill> skills, List<Language> languages) {
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
