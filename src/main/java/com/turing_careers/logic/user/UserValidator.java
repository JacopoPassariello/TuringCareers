package com.turing_careers.logic.user;

import com.turing_careers.data.model.Developer;
import com.turing_careers.data.model.Employer;
import com.turing_careers.logic.utils.LanguageValidator;
import com.turing_careers.logic.utils.SkillValidator;
import com.turing_careers.logic.utils.ValidationException;


import java.util.regex.Pattern;

/**
 * Classe che implementa la validazione di un oggetto User.
 * @author Jacopo Passariello
 */
public class UserValidator {

    private static Pattern MAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$");
    private static Pattern PASS_PATTERN = Pattern.compile("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+");
    private static Pattern NAME_PATTERN = Pattern.compile("^\\s+");

    /**
     * Controlla la validità dei campi dell'utente
     * @author Claudio Gaudino, Jacopo Passariello
     * @param user L'entità da validare.
     * @throws ValidationException Lanciata quando l'entità contiene almeno un campo contenente un valore non valido.
     */
    public static void checkValidity(Developer user) throws ValidationException {
        if (user == null
                || user.getFirstName().length() == 0
                || user.getFirstName().length() > 32
                || NAME_PATTERN.matcher(user.getFirstName()).matches()
                || user.getLastName().length() == 0
                || user.getLastName().length() > 64
                || NAME_PATTERN.matcher(user.getLastName()).matches()
                || user.getBio().length() > 2048
                || user.getMail().length() == 0
                || !MAIL_PATTERN.matcher(user.getMail()).matches()
                || user.getPassword().length() < 8
                || !PASS_PATTERN.matcher(user.getPassword()).matches()
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
        if (user == null
                || user.getFirstName().length() == 0
                || user.getFirstName().length() > 32
                || NAME_PATTERN.matcher(user.getFirstName()).matches()
                || user.getLastName().length() == 0
                || user.getLastName().length() > 64
                || NAME_PATTERN.matcher(user.getLastName()).matches()
                || user.getCompanyName().length() == 0
                || user.getCompanyName().length() > 64
                || !MAIL_PATTERN.matcher(user.getMail()).matches()
                || user.getPassword().length() < 8
                || !PASS_PATTERN.matcher(user.getPassword()).matches()
        ) throw new ValidationException("Employer parameters are not valid!");
    }
}