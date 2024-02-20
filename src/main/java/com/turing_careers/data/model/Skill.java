package com.turing_careers.data.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * Modella l'entità persistente Skill
 */
@Entity
@Table(name = "Skill")
@Getter
@Setter
@NoArgsConstructor
@ToString
@NamedQueries({
    @NamedQuery(name = "indexSkillsByName", query = "SELECT s FROM Skill s WHERE s.skillName LIKE :query"),
})
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "skillId", nullable = false)
    @JsonProperty("_Skill__id")
    private Long skillId;

    @Column(name = "skill_name", nullable = false)
    @JsonProperty("_Skill__name")
    private String skillName;

    @Column(name = "skill_type", nullable = false)
    @JsonProperty("_Skill__type")
    private String skillType;

    public Skill(String name, String skillType) {
        this.skillName = name;
        this.skillType = skillType;
    }
    @ManyToMany(mappedBy = "skills")
    @ToString.Exclude
    // @JsonBackReference("offerSkills")
    @JsonIgnore
    private List<Offer> offerList;

    @ManyToMany(mappedBy = "skills")
    @ToString.Exclude
    // @JsonBackReference("developerSkills")
    @JsonIgnore
    private List<Developer> developerList;
}
