package com.turing_careers.data.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Language")
@Getter
@Setter
@NoArgsConstructor
@ToString
@NamedQueries({
    @NamedQuery(name = "findAllLanguages", query = "SELECT l FROM Language l"),
    @NamedQuery(name = "findLanguageByLanguageCode", query = "SELECT l FROM Language l WHERE l.languageCode = :languageCode")
})
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "languageId", nullable = false)
    private Long id;

    @Column(name = "languageCode", nullable = false)
    private String languageCode;

    public Language(String languageCode) {
        this.languageCode = languageCode;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
