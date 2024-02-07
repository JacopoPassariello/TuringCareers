package com.turing_careers.data.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "Employer")
@Getter
@Setter
@NoArgsConstructor
@ToString
@NamedQueries({
    @NamedQuery(name = "findAllEmployers", query = "SELECT e FROM Employer e"),
    @NamedQuery(name = "findEmplsByMailAndPassword", query = "SELECT e FROM Employer e WHERE e.mail = :mail  AND e.password = :password"),
    @NamedQuery(name = "findEmployerByMail", query = "SELECT e FROM Employer e WHERE e.mail = :mail ")
})
public class Employer implements User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employerId", nullable = false)
    private Long id;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Column(name = "mail", nullable = false)
    private String mail;

    @Column(name = "passwordAccount", nullable = false)
    private String password;

    @Column(name = "companyName", nullable = false)
    private String companyName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employer")
    private List<Offer> offers;

    public Employer(String firstName, String lastName, String mail, String password, String companyName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mail = mail;
        this.password = password;
        this.companyName = companyName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
