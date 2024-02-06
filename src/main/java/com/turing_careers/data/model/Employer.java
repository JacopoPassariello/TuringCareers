package com.turing_careers.data.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "Employer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@NamedQueries({
        @NamedQuery(name="findEmplsByMailAndPassword", query="SELECT e FROM Employer e WHERE e.mail = :mail  AND e.password = :password"),
        @NamedQuery(name="", query=""),
        @NamedQuery(name="", query="")
})
public class Employer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "employerId", nullable = false)
    private int id;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Column(name = "mail", nullable = false)
    private String mail;

    @Column(name = "passwordAccount", nullable = false)
    private String password;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employerId")
    private List<Offer> offers;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
