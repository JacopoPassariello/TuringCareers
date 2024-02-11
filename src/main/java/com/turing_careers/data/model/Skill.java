package com.turing_careers.data.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "Skill")
@Getter
@Setter
@NoArgsConstructor
@ToString
@NamedQueries({
    @NamedQuery(name = "indexSkillsByName", query = "SELECT s FROM Skill s WHERE s.skillName LIKE :query"),
})
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
    private List<Offer> offerList;
    @ManyToMany(mappedBy = "skills")
    @ToString.Exclude
    private List<Developer> developerList;
}
