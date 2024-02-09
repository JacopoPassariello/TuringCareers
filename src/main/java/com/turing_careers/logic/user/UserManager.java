package com.turing_careers.logic.user;

import com.turing_careers.data.dao.DeveloperDAO;
import com.turing_careers.data.dao.EmployerDAO;
import com.turing_careers.data.model.Developer;
import com.turing_careers.data.model.Employer;
import java.util.regex.Pattern;


/**
 * Classe che implementa i servizi di modifica e cancellazione di profili di utenti, oltre all'operazione di salvataggio di un developer da parte di un employer
 */
public class UserManager {

    public static void createProfile(Developer newProfile) throws UpdateProfileException, UserNotValidException {
        checkValidity(newProfile);
        DeveloperDAO updater = DeveloperDAO.getInstance();
        try {
            updater.addDeveloper(newProfile);
        } catch (Exception e) {
            throw new UpdateProfileException(e.getMessage());
        }
    }

    public static void createProfile(Employer newProfile) throws UpdateProfileException, UserNotValidException {
        checkValidity(newProfile);
        EmployerDAO updater = EmployerDAO.getInstance();
        try {
            updater.addEmployer(newProfile);
        } catch (Exception e) {
            throw new UpdateProfileException(e.getMessage());
        }
    }

    /**
     * Controlla la validità dei valori del profilo da aggiornare e lo aggiorna
     * @param newProfile L'Entità che rappresenta la versione aggiornata del profilo
     * @throws UpdateProfileException Lanciata quando si verifica un errore nel tentativo di merge dell'entità.
     * @throws UserNotValidException Lanciata quando almeno uno dei campi di newProfile non è valido.
     */
    public static void editProfile(Developer newProfile) throws UpdateProfileException, UserNotValidException {
        checkValidity(newProfile);
        DeveloperDAO updater = DeveloperDAO.getInstance();
        try {
            updater.updateDeveloper(newProfile);
        } catch (Exception e) {
            throw new UpdateProfileException(e.getMessage());
        }
    }

    /**
     * Controlla la validità dei valori del profilo da aggiornare e lo aggiorna
     * @param newProfile L'Entità che rappresenta la versione aggiornata del profilo
     * @throws UpdateProfileException Lanciata quando si verifica un errore nel tentativo di merge dell'entità.
     * @throws UserNotValidException Lanciata quando almeno uno dei campi di newProfile non è valido.
     */
    public static void editProfile(Employer newProfile) throws UpdateProfileException, UserNotValidException {
        checkValidity(newProfile);
        EmployerDAO updater = EmployerDAO.getInstance();
        try {
            updater.updateEmployer(newProfile);
        } catch (Exception e) {
            throw new UpdateProfileException(e.getMessage());
        }
    }

    /**
     * Rimuove un utente dal sistema.
     * @param user L'Entità rappresentante il profilo dell'utente da rimuovere.
     * @throws DeleteProfileException Lanciata quando la rimozione del profilo non va a buon fine.
     */
    public static void deleteAccount(Developer user) throws DeleteProfileException {
        DeveloperDAO updater = DeveloperDAO.getInstance();
        try {
            updater.removeDeveloper(user);
        } catch (Exception e) {
            throw new DeleteProfileException(e.getMessage());
        }
    }

    /**
     * Rimuove un utente dal sistema.
     * @param user L'Entità rappresentante il profilo dell'utente da rimuovere.
     * @throws DeleteProfileException Lanciata quando la rimozione del profilo non va a buon fine.
     */
    public static void deleteAccount(Employer user) throws DeleteProfileException {
        EmployerDAO updater = EmployerDAO.getInstance();
        try {
            updater.removeEmployer(user);
        } catch (Exception e) {
            throw new DeleteProfileException(e.getMessage());
        }
    }

    /**
     * Implementa l'operazione di salvataggio di un profilo di un developer da parte di un employer
     * @param employer L'Entità che rappresenta l'employer che sta eseguendo l'operazione.
     * @param developer L'Entità che rappresenta il developer che sta venendo salvato.
     * @throws UpdateProfileException
     */
    public static void saveDeveloperProfile(Employer employer, Developer developer) throws UpdateProfileException {
        EmployerDAO updater = EmployerDAO.getInstance();
        if (!employer.getSavedDevelopers().contains(developer)) {
            employer.getSavedDevelopers().add(developer);
            try {
                updater.updateEmployer(employer);
            } catch (Exception ex) {
                throw new UpdateProfileException("Could not add developer " + developer.getId() + " to employer " + employer.getId());
            }
        }
    }

    /**
     * Controlla la validità dei campi dell'utente
     * @param user L'entità da validare.
     * @throws UserNotValidException Lanciata quando l'entità contiene almeno un campo contenente un valore non valido.
     */
    private static void checkValidity(Developer user) throws UserNotValidException {
        Pattern mailPattern = Pattern.compile("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$");

        if (user.getFirstName().length() == 0
                || user.getFirstName().length() > 32
                || user.getLastName().length() == 0
                || user.getLastName().length() > 64
                || user.getBio().length() > 2048
                || user.getMail().length() == 0
                || !mailPattern.matcher(user.getMail()).matches()
                || user.getSkills().isEmpty()
                //TODO: controllare che le skills dello user siano presenti nel database
                || user.getLanguages().isEmpty()
                //TODO: controllare che le lingue dello user siano presenti nel database
        ) throw new UserNotValidException();
    }

    /**
     * Controlla la validità dei campi dell'utente
     * @param user L'entità da validare.
     * @throws UserNotValidException Lanciata quando l'entità contiene almeno un campo contenente un valore non valido.
     */
    private static void checkValidity(Employer user) throws UserNotValidException {
        Pattern mailPattern = Pattern.compile("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$");

        if (user.getFirstName().length() == 0
                || user.getFirstName().length() > 32
                || user.getLastName().length() == 0
                || user.getLastName().length() > 64
                || user.getCompanyName().length() == 0
                || user.getCompanyName().length() > 64
                || !mailPattern.matcher(user.getMail()).matches()
        ) throw new UserNotValidException();
    }

}
