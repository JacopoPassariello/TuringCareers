package com.turing_careers.data.dao;

import com.turing_careers.data.DAO;
import com.turing_careers.data.model.Developer;
import com.turing_careers.data.model.Language;

import java.util.List;
import java.util.Optional;

public class LanguageDAO extends DAO {

    private static LanguageDAO instance;
    private LanguageDAO() {
        super();
    }

    public static synchronized LanguageDAO getInstance() {
        if (instance == null)
            instance = new LanguageDAO();

        return instance;
    }

    public List<Language> getLanguages() {
        return Optional.of(
                super.em
                        .createNamedQuery("findAllLanguages", Language.class)
                        .getResultList()
        ).orElse(null);
    }

    public Developer getLanguageByLanguageCode(String languageCode) {
        return Optional.of(
                super.em
                        .createNamedQuery("findLanguageByLanguageCode", Developer.class)
                        .setParameter("languageCode", languageCode)
                        .getSingleResult()
        ).orElse(null);
    }

    public void addLanguage(Language language) throws Exception {
        try {
            em.getTransaction().begin();
            em.persist(language);
            em.getTransaction().commit();
        } catch (Exception ex) { throw new Exception(ex); }
    }

    public void removeLanguage(Language language) throws Exception {
        try {
            em.getTransaction().begin();
            em.remove(em.merge(language));
            em.getTransaction().commit();
        } catch (Exception ex) { throw new Exception(ex); }
    }

    public void updateLanguage(Language language) throws Exception {
        try {
            em.getTransaction().begin();
            em.merge(language);
            em.getTransaction().commit();
        } catch (Exception ex) { throw new Exception(ex); }
    }
}
