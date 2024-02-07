package com.turing_careers.domain.search;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class SkillMock {
    @JsonProperty("_Skill__id")
    private Long skillId;

    @JsonProperty("_Skill__name")
    private String skillName;

    @JsonProperty("_Skill__type")
    private String skillType;
}
