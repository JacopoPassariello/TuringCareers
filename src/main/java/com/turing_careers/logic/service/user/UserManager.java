package com.turing_careers.logic.service.user;

import com.turing_careers.data.dao.DeveloperDAO;
import com.turing_careers.data.dao.EmployerDAO;
import com.turing_careers.data.dao.PersistenceException;
import com.turing_careers.data.model.Developer;
import com.turing_careers.data.model.Employer;
import com.turing_careers.data.model.Offer;
import com.turing_careers.logic.service.utils.ValidationException;

import java.util.List;


/**
 * Classe che implementa i servizi di modifica e cancellazione di profili di utenti, oltre all'operazione di salvataggio di un developer da parte di un employer
 * @author Claudio Gaudino
 */
public class UserManager {
    /**
     * @param newProfile Entità che rappresenta il nuovo profilo.
     * @throws PersistenceException quando si verifica un errore nel tentativo di persistenza dell'entità.
     * @throws ValidationException Lanciata quando almeno uno dei campi di newProfile non è valido.
     */
    public static void createProfile(Developer newProfile) throws PersistenceException, ValidationException {
        UserValidator.checkValidity(newProfile);
        DeveloperDAO updater = DeveloperDAO.getInstance();
        updater.addDeveloper(newProfile);
    }
    /**
     * Controlla la validità dei valori del profilo da aggiornare e lo aggiorna
     * @param newProfile L'Entità che rappresenta il nuovo profilo.
     * @throws PersistenceException Lanciata quando si verifica un errore nel tentativo di persistenza dell'entità.
     * @throws ValidationException Lanciata quando almeno uno dei campi di newProfile non è valido.
     */
    public static void createProfile(Employer newProfile) throws PersistenceException, ValidationException {
        UserValidator.checkValidity(newProfile);
        EmployerDAO updater = EmployerDAO.getInstance();
        updater.addEmployer(newProfile);
    }

    /**
     * Controlla la validità dei valori del profilo da aggiornare e lo aggiorna
     * @param editedProfile L'Entità che rappresenta la versione aggiornata del profilo
     * @throws PersistenceException Lanciata quando si verifica un errore nel tentativo di merge dell'entità.
     * @throws ValidationException Lanciata quando almeno uno dei campi di newProfile non è valido.
     */
    public static void editProfile(Developer editedProfile) throws PersistenceException, ValidationException {
        UserValidator.checkValidity(editedProfile);
        DeveloperDAO updater = DeveloperDAO.getInstance();
        updater.updateDeveloper(editedProfile);
    }

    /**
     * Controlla la validità dei valori del profilo da aggiornare e lo aggiorna
     * @param editedProfile L'Entità che rappresenta la versione aggiornata del profilo
     * @throws PersistenceException Lanciata quando si verifica un errore nel tentativo di merge dell'entità.
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
     * @throws PersistenceException lanciata quando si verifica un'errore nell'aggiunta del profilo alla lista dei Developer salvati.
     */
    public static void saveDeveloperProfile(Employer employer, Developer developer) throws PersistenceException {
        EmployerDAO updater = EmployerDAO.getInstance();
        if (!employer.getSavedDevelopers().contains(developer)) {
            employer.getSavedDevelopers().add(developer);
            updater.updateEmployer(employer);
        }
    }
    /**
     * @author Jacopo Passariello
     * @param mail indirizzo e-mail del developer usato per il retrieve.
     * @return il profilo di developer a cui corrisponde l'indirizzo e-mail.
     */
    public static Developer getDeveloperByMail(String mail) {
        DeveloperDAO retriever = DeveloperDAO.getInstance();
        return retriever.getDeveloperByMail(mail);
    }
    /**
     * @author Jacopo Passariello
     * @param mail indirizzo e-mail dell'employer usato per il retrieve.
     * @return il profilo dell'employer a cui corrisponde l'indirizzo e-mail.
     */
    public static Employer getEmployerByMail(String mail) {
        EmployerDAO retriever = EmployerDAO.getInstance();
        return retriever.getEmployerByMail(mail);
    }

    /**
     * @author Antonino Lorenzo
     * @return offers managed by employer
     * */
    public static List<Offer> getEmployerOffers(Employer employer) {
        EmployerDAO employerDAO = EmployerDAO.getInstance();
        return employerDAO.getEmployerOffers(employer);
    }

    /*
    public static Developer retrieveDeveloperByMail(String mail) throws UpdateProfileException, PersistenceException {
        DeveloperDAO updater = DeveloperDAO.getInstance();
        Developer dev = updater.getDeveloperByMail(mail);
        Argon2Encryption encryptor = new Argon2Encryption();
        return dev;
    }

    public static Employer retrieveEmployerByMail(String mail) throws UpdateProfileException, PersistenceException {
        EmployerDAO updater = EmployerDAO.getInstance();
        Employer emp = updater.getEmployerByMail(mail);
        return emp;
    }
    */
}
