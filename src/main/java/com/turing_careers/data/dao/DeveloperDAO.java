package com.turing_careers.data.dao;

import com.turing_careers.data.DAO;
import com.turing_careers.data.model.*;

import java.util.List;
import java.util.Optional;

public class DeveloperDAO extends DAO {

    private static DeveloperDAO instance;
    private DeveloperDAO() {
        super();
    }

    /**
     * @return Istanza condivisa da tutti i DeveloperDAO
     */
    public static synchronized DeveloperDAO getInstance() {
        if (instance == null)
            instance = new DeveloperDAO();

        return instance;
    }

    /**
     * @return Una lista contenente ogni istanza di Developer
     */
    public List<Developer> getDevelopers() {
        return Optional.of(
                super.em
                        .createNamedQuery("findAllDevelopers", Developer.class)
                        .getResultList()
        ).orElse(null);
    }

    /**
     * @param mail La e-mail da usare per recuperare il Developer
     * @return L'istanza di developer contenente la e-mail passata come argomento
     */
    public Developer getDeveloperByMail(String mail) {
        return Optional.of(
                super.em
                        .createNamedQuery("findDeveloperByMail", Developer.class)
                        .setParameter("mail", mail)
                        .getSingleResult()// Questo implica che mail è unique?
        ).orElse(null);
    }

    /**
     * @param id L'id da usare per recuperare il Developer
     * @return L'istanza di developer contenente l'id passato come argomento
     */
    public Developer getDeveloperById(Long id) {
        return Optional.of(
                super.em
                        .createNamedQuery("findDeveloperById", Developer.class)
                        .setParameter("id", id)
                        .getSingleResult()
        ).orElse(null);
    }

    /**
     * Aggiunge uno sviluppatore al database
     * @param developer L'istanza di sviluppatore da aggiungere
     * @throws PersistenceException Lanciata quando avviene un errore nel tentativo di aggiunta.
     */
    public void addDeveloper(Developer developer) throws PersistenceException  {
        // Create Location it was never found

        LocationDAO locationDAO = LocationDAO.getInstance();
        Location devLoc;
        try {
            // TODO: get by name ??????????
            devLoc = locationDAO.getLocationByLatAndLon(
                    developer.getLocation().getLat(),
                    developer.getLocation().getLon()
            );
        } catch (Exception ex) {
            System.out.println("Location Not Found, Adding: " + developer.getLocation().getName());
            devLoc = new Location(
                    developer.getLocation().getName(),
                    developer.getLocation().getLat(),
                    developer.getLocation().getLon()
            );
            // devLoc = em.merge(devLoc);
            locationDAO.addLocation(devLoc);
        }
        /*
        devLoc = locationDAO.getLocationByLatAndLon(devLoc.getLat(), devLoc.getLon());
        System.out.println(devLoc);
        developer.setLocation(devLoc);
        System.out.println(developer);*/
        try {
            em.getTransaction().begin();

            for (Skill s : developer.getSkills())
                em.merge(s);

            for (Language l : developer.getLanguages())
                em.merge(l);

            em.merge(developer.getLocation());

            em.persist(developer);
            em.getTransaction().commit();
        } catch (Exception ex) { throw new PersistenceException(ex.getMessage()); }
    }

    /**
     * Rimuove uno sviluppatore dal database
     * @param developer L'istanza di sviluppatore da rimuovere
     * @throws PersistenceException Lanciata quando avviene un errore nel tentativo di rimozione
     */
    public void removeDeveloper(Developer developer) throws PersistenceException {
        try {
            em.getTransaction().begin();
            em.remove(em.merge(developer));
            em.getTransaction().commit();
        } catch (Exception ex) { throw new PersistenceException(ex.getMessage()); }
    }

    /**
     * Aggiorna lo stato di uno sviluppatore nel database
     * @param developer L'istanza di sviluppatore da aggiornare
     * @throws PersistenceException Lanciata quando avviene un errore nel tentativo di aggiornamento
     */
    public void updateDeveloper(Developer developer) throws PersistenceException {
        try {
            em.getTransaction().begin();
            em.merge(developer);
            em.getTransaction().commit();
        } catch (Exception ex) { throw new PersistenceException(ex.getMessage()); }
    }
}
