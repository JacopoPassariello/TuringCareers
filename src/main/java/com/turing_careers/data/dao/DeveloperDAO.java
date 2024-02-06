package com.turing_careers.data.dao;

import com.turing_careers.data.DAO;
import com.turing_careers.data.model.Developer;
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

    public Developer getDeveloperByMailAndPassword(String mail, String password) {
        return Optional.of(
                super.em
                        .createNamedQuery("findDevsByMailAndPassword", Developer.class)
                        .setParameter("mail", mail)
                        .setParameter("password", password)
                        .getSingleResult()
        ).orElse(null);
    }

    public void addDeveloper(Developer developer) throws Exception {
        try {
            em.getTransaction().begin();
            em.persist(developer);
            em.getTransaction().commit();
        } catch (Exception ex) { throw new Exception(ex); }
    }

    public void removeDeveloper(Developer developer) throws Exception {
        try {
            em.getTransaction().begin();
            em.remove(em.merge(developer));
            em.getTransaction().commit();
        } catch (Exception ex) { throw new Exception(ex); }
    }

    public void updateDeveloper(Developer developer) throws Exception {
        try {
            em.getTransaction().begin();
            em.merge(developer);
            em.getTransaction().commit();
        } catch (Exception ex) { throw new Exception(ex); }
    }
}
