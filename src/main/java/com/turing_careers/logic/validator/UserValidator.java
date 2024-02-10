package com.turing_careers.logic.validator;

import com.turing_careers.data.model.Developer;
import com.turing_careers.data.model.Employer;


import java.util.regex.Pattern;

/**
 * Classe che implementa la validazione di un oggetto User.
 * @author Jacopo Passariello
 */
public class UserValidator {
    /**
     * Controlla la validità dei campi dell'utente
     * @author Claudio Gaudino, Jacopo Passariello
     * @param user L'entità da validare.
     * @throws ValidationException Lanciata quando l'entità contiene almeno un campo contenente un valore non valido.
     */
    public static void checkValidity(Developer user) throws ValidationException {
            Pattern mailPattern = Pattern.compile("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$");
            Pattern namePattern = Pattern.compile("([A-Za-z]+( [A-Za-z]+)+)");


            if (user == null
                    || user.getFirstName().length() == 0
                    || user.getFirstName().length() > 32
                    || !namePattern.matcher(user.getFirstName()).matches()
                    || user.getLastName().length() == 0
                    || user.getLastName().length() > 64
                    || !namePattern.matcher(user.getLastName()).matches()
                    || user.getBio().length() > 2048
                    || user.getMail().length() == 0
                    || !mailPattern.matcher(user.getMail()).matches()
                    || user.getSkills().isEmpty()
                    || user.getLanguages().isEmpty()
                    || user.getLocation() == null // Aggiunto check su location
            ) throw new ValidationException("Developer parameters are not valid!");
            //CHECKME: nuovo blocco di codice per la validazione di skill e language
            LanguageValidator.validateLanguages(user.getLanguages());
        SkillValidator.validateSkills(user.getSkills());

    }

    /**
     * Controlla la validità dei campi dell'utente
     * @param user L'entità da validare.
     * @throws ValidationException Lanciata quando l'entità contiene almeno un campo contenente un valore non valido.
     */
    public static void checkValidity(Employer user) throws ValidationException {
        Pattern mailPattern = Pattern.compile("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$");
        Pattern namePattern = Pattern.compile("([A-Za-z]+( [A-Za-z]+)+)");

        if (user == null
                || user.getFirstName().length() == 0
                || user.getFirstName().length() > 32
                || !namePattern.matcher(user.getFirstName()).matches()
                || user.getLastName().length() == 0
                || user.getLastName().length() > 64
                || !namePattern.matcher(user.getLastName()).matches()
                || user.getCompanyName().length() == 0
                || user.getCompanyName().length() > 64
                || !mailPattern.matcher(user.getMail()).matches()
        ) throw new ValidationException("Employer parameters are not valid!");
    }
}
