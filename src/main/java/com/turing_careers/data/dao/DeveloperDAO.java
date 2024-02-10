package com.turing_careers.data.dao;

import com.turing_careers.data.DAO;
import com.turing_careers.data.model.Developer;
import com.turing_careers.data.model.Employer;
import com.turing_careers.data.model.Skill;

import java.util.List;
import java.util.Optional;

public class DeveloperDAO extends DAO {

    private static DeveloperDAO instance;
    private DeveloperDAO() {
        super();
    }

    public static synchronized DeveloperDAO getInstance() {
        if (instance == null)
            instance = new DeveloperDAO();

        return instance;
    }

    public List<Developer> getDevelopers() {
        return Optional.of(
                super.em
                        .createNamedQuery("findAllDevelopers", Developer.class)
                        .getResultList()
        ).orElse(null);
    }

    public Developer getDeveloperByMail(String mail) {
        return Optional.of(
                super.em
                        .createNamedQuery("findDeveloperByMail", Developer.class)
                        .setParameter("mail", mail)
                        .getSingleResult()// Questo implica che mail è unique?
        ).orElse(null);
    }

    public void addDeveloper(Developer developer) throws PersistenceException  {
        try {
            em.getTransaction().begin();
            em.persist(developer);
            em.getTransaction().commit();
        } catch (Exception ex) { throw new PersistenceException(ex.getMessage()); }
    }

    public void removeDeveloper(Developer developer) throws PersistenceException {
        try {
            em.getTransaction().begin();
            em.remove(em.merge(developer));
            em.getTransaction().commit();
        } catch (Exception ex) { throw new PersistenceException(ex.getMessage()); }
    }

    public void updateDeveloper(Developer developer) throws PersistenceException {
        try {
            em.getTransaction().begin();
            em.merge(developer);
            em.getTransaction().commit();
        } catch (Exception ex) { throw new PersistenceException(ex.getMessage()); }
    }
}
