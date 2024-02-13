package com.turing_careers.logic.service.utils;

import com.turing_careers.data.dao.SkillDAO;
import com.turing_careers.data.model.Skill;

import java.util.List;

public class SkillMatcher {
    public static List<Skill> getSuggestions(String query) {
        return SkillDAO
                .getInstance()
                .getSkillsByName(query);
    }
}
