package com.turing_careers.data.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Skill")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "skillId", nullable = false)
    private Long skillId;

    @Column
    private String skillName;

    @Column
    private String skillType;
}
