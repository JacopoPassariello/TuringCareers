package com.turing_careers.data;

import jakarta.persistence.*;

public abstract class DAO {
    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    protected EntityManager em;
    private static EntityManagerFactory emf;

    public DAO() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("turing_careers");
        }

        this.em = emf.createEntityManager();
    }

    public void closeEntityManager() {
        em.close();
    }

    protected void closeEntityManagerFactory() {
        if (emf != null)
            emf.close();
    }
}
