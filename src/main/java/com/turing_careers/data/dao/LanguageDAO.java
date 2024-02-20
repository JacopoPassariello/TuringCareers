package com.turing_careers.data.dao;

import com.turing_careers.data.model.Language;

import java.util.List;
import java.util.Optional;

/**
 * Classe che implementa la gestione della persistenza dell'entità data.model.Language
 */
public class LanguageDAO extends DAO {

    private static LanguageDAO instance;
    private LanguageDAO() {
        super();
    }

    /**
     * Implementazione del design pattern Singleton
     * @return Istanza condivisa da tutti gli LanguageDAO
     */
    public static synchronized LanguageDAO getInstance() {
        if (instance == null)
            instance = new LanguageDAO();

        return instance;
    }

    /**
     * @return Una lista contenente ogni istanza di Language
     */
    public List<Language> getLanguages() {
        return Optional.of(
                super.em
                        .createNamedQuery("findAllLanguages", Language.class)
                        .getResultList()
        ).orElse(null);
    }

    /**
     * @param languageCode Il language code da usare per recuperare il linguaggio
     * @return L'istanza di Language che contiene languageCode
     */
    public Language getLanguageByLanguageCode(String languageCode) {
        return Optional.of(
                super.em
                        .createNamedQuery("findLanguageByLanguageCode", Language.class)
                        .setParameter("languageCode", languageCode)
                        .getSingleResult()
        ).orElse(null);
    }

    /**
     * Aggiunge un Language al database
     * @param language L'istanza di Language da aggiungere
     * @throws PersistenceException Lanciata quando avviene un errore durante l'aggiunta
     */
    public void addLanguage(Language language) throws PersistenceException {
        try {
            em.getTransaction().begin();
            em.persist(language);
            em.getTransaction().commit();
        } catch (Exception ex) { throw new PersistenceException(ex); }
    }

    /**
     * Rimuove un Language dal database
     * @param language L'istanza di Language da rimuovere
     * @throws PersistenceException Lanciata quando avviene un errore durante la rimozione
     */
    public void removeLanguage(Language language) throws PersistenceException {
        try {
            em.getTransaction().begin();
            em.remove(em.merge(language));
            em.getTransaction().commit();
        } catch (Exception ex) { throw new PersistenceException(ex.getMessage()); }
    }

    /**
     * Aggiorna un Language nel database
     * @param language L'istanza di Language da aggiornare
     * @throws PersistenceException Lanciata quando avviene un errore durante l'aggiornamento
     */
    public void updateLanguage(Language language) throws PersistenceException {
        try {
            em.getTransaction().begin();
            em.merge(language);
            em.getTransaction().commit();
        } catch (Exception ex) { throw new PersistenceException(ex.getMessage()); }
    }
}
