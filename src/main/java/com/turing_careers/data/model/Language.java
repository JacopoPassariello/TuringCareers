package com.turing_careers.data.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Language")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "languageId", nullable = false)
    private int id;

    @Column(name = "languageCode", nullable = false)
    private String languageCode;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
