package com.turing_careers.logic.user;

import com.turing_careers.data.dao.DeveloperDAO;
import com.turing_careers.data.dao.EmployerDAO;
import com.turing_careers.data.dao.PersistenceException;
import com.turing_careers.data.model.Developer;
import com.turing_careers.data.model.Employer;
import com.turing_careers.logic.validator.LanguageValidator;
import com.turing_careers.logic.validator.SkillValidator;
import com.turing_careers.logic.validator.UserValidator;
import com.turing_careers.logic.validator.ValidationException;

import java.util.regex.Pattern;


/**
 * Classe che implementa i servizi di modifica e cancellazione di profili di utenti, oltre all'operazione di salvataggio di un developer da parte di un employer
 */
public class UserManager {
    public static void createProfile(Developer newProfile) throws PersistenceException, ValidationException {
        UserValidator.checkValidity(newProfile);
        DeveloperDAO updater = DeveloperDAO.getInstance();
        updater.addDeveloper(newProfile);
    }
    /**
     * Controlla la validità dei valori del profilo da aggiornare e lo aggiorna
     * @param newProfile L'Entità che rappresenta il nuovo profilo.
     * @throws UpdateProfileException Lanciata quando si verifica un errore nel tentativo di aggiunta dell'entità.
     * @throws UserNotValidException Lanciata quando almeno uno dei campi di newProfile non è valido.
     */
    public static void createProfile(Employer newProfile) throws PersistenceException, ValidationException {
        UserValidator.checkValidity(newProfile);
        EmployerDAO updater = EmployerDAO.getInstance();
        updater.addEmployer(newProfile);
    }

    /**
     * Controlla la validità dei valori del profilo da aggiornare e lo aggiorna
     * @param editedProfile L'Entità che rappresenta la versione aggiornata del profilo
     * @throws UpdateProfileException Lanciata quando si verifica un errore nel tentativo di merge dell'entità.
     * @throws UserNotValidException Lanciata quando almeno uno dei campi di newProfile non è valido.
     */
    public static void editProfile(Developer editedProfile) throws PersistenceException, ValidationException {
        UserValidator.checkValidity(editedProfile);
        DeveloperDAO updater = DeveloperDAO.getInstance();
        updater.updateDeveloper(editedProfile);
    }

    /**
     * Controlla la validità dei valori del profilo da aggiornare e lo aggiorna
     * @param editedProfile L'Entità che rappresenta la versione aggiornata del profilo
     * @throws UpdateProfileException Lanciata quando si verifica un errore nel tentativo di merge dell'entità.
     * @throws ValidationException Lanciata quando almeno uno dei campi di newProfile non è valido.
     */
    public static void editProfile(Employer editedProfile) throws PersistenceException, ValidationException {
        UserValidator.checkValidity(editedProfile);
        EmployerDAO updater = EmployerDAO.getInstance();
        updater.updateEmployer(editedProfile);
    }

    /**
     * Rimuove un utente dal sistema.
     * @param user L'Entità rappresentante il profilo dell'utente da rimuovere.
     * @throws DeleteProfileException Lanciata quando la rimozione del profilo non va a buon fine.
     */
    public static void deleteAccount(Developer user) throws DeleteProfileException, PersistenceException {
        if (user.getId() == null) {
            throw new DeleteProfileException("User ID is null!");
        }
        DeveloperDAO updater = DeveloperDAO.getInstance();
        updater.removeDeveloper(user);

    }

    /**
     * Rimuove un utente dal sistema.
     * @param user L'Entità rappresentante il profilo dell'utente da rimuovere.
     * @throws DeleteProfileException Lanciata quando la rimozione del profilo non va a buon fine.
     */
    public static void deleteAccount(Employer user) throws DeleteProfileException, PersistenceException {
        if (user.getId() == null) {
            throw new DeleteProfileException("User ID is null!");
        }
        EmployerDAO updater = EmployerDAO.getInstance();
        updater.removeEmployer(user);

    }

    /**
     * Implementa l'operazione di salvataggio di un profilo di un developer da parte di un employer
     * @param employer L'Entità che rappresenta l'employer che sta eseguendo l'operazione.
     * @param developer L'Entità che rappresenta il developer che sta venendo salvato.
     * @throws UpdateProfileException lanciata quando si verifica un'errore nell'aggiunta del profilo alla lista dei Developer salvati.
     */
    public static void saveDeveloperProfile(Employer employer, Developer developer) throws UpdateProfileException, PersistenceException {
        EmployerDAO updater = EmployerDAO.getInstance();
        if (!employer.getSavedDevelopers().contains(developer)) {
            employer.getSavedDevelopers().add(developer);
            updater.updateEmployer(employer);

        }
    }
}
