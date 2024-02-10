package com.turing_careers.data.dao;

import com.turing_careers.data.DAO;
import com.turing_careers.data.model.Skill;

import java.util.List;
import java.util.Optional;

public class SkillDAO extends DAO {
    private static SkillDAO instance;
    private SkillDAO() {
        super();
    }

    public static synchronized SkillDAO getInstance() {
        if (instance == null)
            instance = new SkillDAO();

        return instance;
    }

    public List<Skill> getSkills() {
        return Optional.of(
                super.em
                        .createQuery("SELECT s FROM Skill s", Skill.class)
                        .getResultList()
        ).orElse(null);
    }

    public List<Skill> getSkillsByName(String query) {
        System.out.println("Query to skills: " + query);
        return super.em
                .createNamedQuery("indexSkillsByName", Skill.class)
                .setParameter("query", "%" + query + "%")
                .getResultList();
    }

    public void addSkill(Skill skill) throws PersistenceException {
        try {
            em.getTransaction().begin();
            em.persist(skill);
            em.getTransaction().commit();
        } catch (Exception ex) { throw new PersistenceException(ex.getMessage()); }
    }

    public void removeSkill(Skill skill) throws PersistenceException {
        try {
            em.getTransaction().begin();
            em.remove(em.merge(skill));
            em.getTransaction().commit();
        } catch (Exception ex) { throw new PersistenceException(ex.getMessage()); }
    }

    public void updateSkill(Skill skill) throws PersistenceException {
        try {
            em.getTransaction().begin();
            em.merge(skill);
            em.getTransaction().commit();
        } catch (Exception ex) { throw new PersistenceException(ex.getMessage()); }
    }
}
