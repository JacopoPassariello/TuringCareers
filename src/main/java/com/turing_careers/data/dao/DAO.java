package com.turing_careers.data.dao;

import jakarta.persistence.*;

/**
 * Classe astratta che si occupa di fornire funzionalità comuni a tutte le classi DAO
 */
public abstract class DAO {
    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    protected EntityManager em;
    private static EntityManagerFactory emf;

    /**
     * Costruttore che implementa la condivisione di un singolo EntityManagerFactory da tutte le istanze della classe ed istanzia un nuovo EntityManager.
     */
    public DAO() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("turing_careers");
        }

        this.em = emf.createEntityManager();
    }

    /**
     * Chiude l'EntityManager
     */
    public void closeEntityManager() {
        em.close();
    }

    /**
     * Chiude l'EntityManagerFactory
     */
    protected void closeEntityManagerFactory() {
        if (emf != null)
            emf.close();
    }
}
