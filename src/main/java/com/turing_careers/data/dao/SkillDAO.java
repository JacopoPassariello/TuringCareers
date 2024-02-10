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

    /**
     * @return Istanza condivisa da ogni SkillDAO
     */
    public static synchronized SkillDAO getInstance() {
        if (instance == null)
            instance = new SkillDAO();

        return instance;
    }

    /**
     * @return Una lista contenente ogni istanza di Skill
     */
    public List<Skill> getSkills() {
        return Optional.of(
                super.em
                        .createQuery("SELECT s FROM Skill s", Skill.class)
                        .getResultList()
        ).orElse(null);
    }

    /**
     * @param name Il nome della Skill da recuperare
     * @return L'istanza di Skill contenente name come nome
     */
    public List<Skill> getSkillsByName(String name) {
        return super.em
                .createNamedQuery("indexSkillsByName", Skill.class)
                .setParameter("query", "%" + name + "%")
                .getResultList();
    }

    /**
     * Aggiunge una Skill al database
     * @param skill L'istanza di Skill da aggiungere
     * @throws PersistenceException Lanciata quando avviene un errore durante l'aggiunta
     */
    public void addSkill(Skill skill) throws PersistenceException {
        try {
            em.getTransaction().begin();
            em.persist(skill);
            em.getTransaction().commit();
        } catch (Exception ex) { throw new PersistenceException(ex.getMessage()); }
    }

    /**
     * Rimuove una Skill dal database
     * @param skill L'ìstanza di Skill da rimuovere
     * @throws PersistenceException Lanciata quando avviene un errore durante la rimozione
     */
    public void removeSkill(Skill skill) throws PersistenceException {
        try {
            em.getTransaction().begin();
            em.remove(em.merge(skill));
            em.getTransaction().commit();
        } catch (Exception ex) { throw new PersistenceException(ex.getMessage()); }
    }

    /**
     * Aggiorna una Skill nel database
     * @param skill L'istanza di Skill da aggiornare
     * @throws PersistenceException Lanciata quando avviene un errore durante l'aggiornamento
     */
    public void updateSkill(Skill skill) throws PersistenceException {
        try {
            em.getTransaction().begin();
            em.merge(skill);
            em.getTransaction().commit();
        } catch (Exception ex) { throw new PersistenceException(ex.getMessage()); }
    }
}
