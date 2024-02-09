package com.turing_careers.logic.validator;

import com.turing_careers.data.dao.SkillDAO;
import com.turing_careers.data.model.Skill;

import java.util.List;

/**
 * Classe che implementa la validazione di skill da effettuare quando vanno creati/aggiornati profili di Dev oppure Offerte.
 */
public class SkillValidator {
    /**
     * Controlla che ogni elemento della lista di skill passate sia presente nel database.
     * @author Jacopo Passariello
     * @param skillList lista di skill da validare.
     * @throws ValidationException eccezione lanciata quando una skill non è presente nel db.
     */
    public static void validateSkills(List<Skill> skillList) throws ValidationException {
        SkillDAO validator = SkillDAO.getInstance();
        List<Skill> dbSkills = validator.getSkills();
        for (Skill s : skillList) {
            if (!dbSkills.contains(s)) {
                throw new ValidationException("Input skill is not present in DB!");
            }
        }
    }
}
