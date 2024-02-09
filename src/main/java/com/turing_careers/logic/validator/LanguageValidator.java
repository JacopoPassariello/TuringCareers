package com.turing_careers.logic.validator;

import com.turing_careers.data.dao.LanguageDAO;
import com.turing_careers.data.dao.SkillDAO;
import com.turing_careers.data.model.Language;

import java.util.List;

/**
 * Classe che implementa la validazione di language da effettuare quando vanno creati/aggiornati profili di Dev oppure Offerte.
 */
public class LanguageValidator {
    /**
     * Controlla che ogni elemento della lista di lingue passate sia presente nel database.
     * @author Jacopo Passariello
     * @param languageList lista di lingue da validare.
     * @throws ValidationException eccezione lanciata quando incontra una lingua non presente nel database.

     **/
    public void validateLanguages(List<Language> languageList) throws ValidationException {
        LanguageDAO validator = LanguageDAO.getInstance();
        List<Language> dbLangs = validator.getLanguages();
        for (Language l : languageList) {
            if (!dbLangs.contains(l)) {
                throw new ValidationException("Input Langugage is not present in DB!");
            }
        }

    }
}
