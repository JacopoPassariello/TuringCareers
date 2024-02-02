package UT_dao;


import com.turing_careers.data.dao.SkillDAO;
import com.turing_careers.data.model.Skill;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TestSkillDAO {
    @Test
    public void getSkillsTest() {
        SkillDAO skillDAO = SkillDAO.getInstance();
        List<Skill> skills = skillDAO.getSkills();

        Assertions.assertNotNull(skills);
    }
}
