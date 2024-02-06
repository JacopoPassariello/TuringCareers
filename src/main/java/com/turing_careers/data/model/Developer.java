package com.turing_careers.data.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "Developer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@NamedQueries({
    @NamedQuery(name = "findAllDevelopers", query = "SELECT d FROM Developer d"),
    @NamedQuery(name = "findDevsByMailAndPassword", query = "SELECT d FROM Developer d WHERE d.mail = :mail  AND d.password = :password")
})
public class Developer implements User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "developerId", nullable = false)
    private int id;

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

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
