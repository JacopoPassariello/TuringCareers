package com.turing_careers.data.dao;

import com.turing_careers.data.DAO;
import com.turing_careers.data.model.Skill;

import java.util.List;
import java.util.Optional;

public class SkillDAO extends DAO {
    private static SkillDAO instance;
    private SkillDAO() {
        super();
    }

    public static synchronized SkillDAO getInstance() {
        if (instance == null)
            instance = new SkillDAO();

        return instance;
    }

    public List<Skill> getSkills() {
        return Optional.of(
                super.em
                        .createQuery("SELECT * FROM Skills", Skill.class)
                        .getResultList()
        ).orElse(null);
    }
}
