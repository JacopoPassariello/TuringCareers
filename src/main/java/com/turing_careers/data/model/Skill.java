package com.turing_careers.data.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Skill")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "skillId", nullable = false)
    private int skillId;

    @Column(name = "skill_name", nullable = false)
    private String skillName;

    @Column(name = "skill_type", nullable = false)
    private String skillType;

    public Skill(String name, String skillType) {
        this.skillName = name;
        this.skillType = skillType;
    }
}
