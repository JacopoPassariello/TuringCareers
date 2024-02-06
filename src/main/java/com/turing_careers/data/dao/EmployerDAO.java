package com.turing_careers.data.dao;

import com.turing_careers.data.DAO;
import com.turing_careers.data.model.Developer;
import com.turing_careers.data.model.Employer;

import java.util.List;
import java.util.Optional;

public class EmployerDAO extends DAO {

    private static EmployerDAO instance;
    private EmployerDAO() {
        super();
    }

    public static synchronized EmployerDAO getInstance() {
        if (instance == null)
            instance = new EmployerDAO();

        return instance;
    }

    public List<Employer> getEmployers() {
        return Optional.of(
                super.em
                        .createNamedQuery("findAllEmployers", Employer.class)
                        .getResultList()
        ).orElse(null);
    }

    public Employer getEmployersByMailAndPassword() {
        return Optional.of(
                super.em
                        .createNamedQuery("findEmplsByMailAndPassword", Employer.class)
                        .getSingleResult()
        ).orElse(null);
    }

    public void addEmployer(Employer employer) throws Exception {
        try {
            em.getTransaction().begin();
            em.persist(employer);
            em.getTransaction().commit();
        } catch (Exception ex) { throw new Exception(ex); }
    }
}
