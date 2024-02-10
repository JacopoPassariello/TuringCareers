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

    /**
     * @return Istanza condivisa da tutti gli EmployerDAO
     */
    public static synchronized EmployerDAO getInstance() {
        if (instance == null)
            instance = new EmployerDAO();

        return instance;
    }

    /**
     * @return Una lista contenente ogni istanza di Employer
     */
    public List<Employer> getEmployers() {
        return Optional.of(
                super.em
                        .createNamedQuery("findAllEmployers", Employer.class)
                        .getResultList()
        ).orElse(null);
    }

    /**
     * @param mail La e-mail da usare per recuperare l'Employer
     * @return L'istanza di Employer contenente la e-mail passata come argomento
     */
    public Employer getEmployerByMail(String mail) {
        return Optional.of(
                super.em
                        .createNamedQuery("findEmployerByMail", Employer.class)
                        .setParameter("mail", mail)
                        .getSingleResult()
        ).orElse(null);
    }

    /**
     * @param id L'id da usare per recuperare l'Employer
     * @return L'istanza di Employer contenente l'id passato come argomento
     */
    public Employer getEmployerById(Long id) {
        return Optional.of(
                super.em
                        .createNamedQuery("findEmployerById", Employer.class)
                        .setParameter("id", id)
                        .getSingleResult()
        ).orElse(null);
    }

    /**
     * Aggiunge un Employer al database
     * @param employer L'istanza di Employer da aggiungere
     * @throws PersistenceException Lanciata quando avviene un errore nel tentativo di aggiunta.
     */
    public void addEmployer(Employer employer) throws PersistenceException {
        try {
            em.getTransaction().begin();
            em.persist(employer);
            em.getTransaction().commit();
        } catch (Exception ex) { throw new PersistenceException(ex.getMessage()); }
    }

    /**
     * Rimuove un Employer dal database
     * @param employer L'istanza di Employer da rimuovere
     * @throws PersistenceException Lanciata quando avviene un errore nel tentativo di rimozione.
     */
    public void removeEmployer(Employer employer) throws PersistenceException {
        try {
            em.getTransaction().begin();
            em.remove(em.merge(employer));
            em.getTransaction().commit();
        } catch (Exception ex) { throw new PersistenceException(ex.getMessage()); }
    }

    /**
     * Aggiurna un Employer nel database
     * @param employer L'istanza di Employer da aggiornare
     * @throws PersistenceException Lanciata quando avviene un errore nel tentativo di aggiornamento.
     */
    public void updateEmployer(Employer employer) throws PersistenceException {
        try {
            em.getTransaction().begin();
            em.merge(employer);
            em.getTransaction().commit();
        } catch (Exception ex) { throw new PersistenceException(ex.getMessage()); }
    }
}
