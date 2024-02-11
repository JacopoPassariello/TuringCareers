package com.turing_careers.logic.validator;

import com.turing_careers.data.dao.LanguageDAO;
import com.turing_careers.data.model.Language;

import java.util.List;

/**
 * Classe che implementa la validazione di language da effettuare quando vanno creati/aggiornati profili di Dev oppure Offerte.
 * @author Jacopo Passariello
 */
public class LanguageValidator {
    /**
     * Controlla che ogni elemento della lista di lingue passate sia presente nel database.
     * @author Jacopo Passariello
     * @param languageList lista di lingue da validare.
     * @throws ValidationException eccezione lanciata quando incontra una lingua non presente nel database.

     **/
    public static void validateLanguages(List<Language> languageList) throws ValidationException {
        if (languageList.isEmpty()) {
            throw new ValidationException("Language List is Empty");
        }
        LanguageDAO validator = LanguageDAO.getInstance();
        List<Language> dbLangs = validator.getLanguages();
        for (Language l : languageList) {
            boolean valid = false;
            for (Language dbl : dbLangs) {
                if (l.getLanguageCode().equals(dbl.getLanguageCode())) {
                    valid = true;
                    l.setId(dbl.getId());
                    break;
                }
            }
            if (!valid) throw new ValidationException("Input Langugage is not present in DB!");
        }

    }
}
