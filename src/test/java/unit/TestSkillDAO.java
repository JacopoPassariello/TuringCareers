package unit;


import com.turing_careers.data.dao.PersistenceException;
import com.turing_careers.data.dao.SkillDAO;
import com.turing_careers.data.model.Skill;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TestSkillDAO {
    @Test
    public void addSkillTest() throws PersistenceException {
        SkillDAO skillDAO = SkillDAO.getInstance();
        Skill skill = new Skill("Python", "Programming Language");

        try {
            skillDAO.addSkill(skill);
        } catch (PersistenceException ex) {
            Assertions.fail();
        }

        List<Skill> skills = skillDAO.getSkills();
        Assertions.assertFalse(skills.isEmpty());
        System.out.println(skills);
    }
}
