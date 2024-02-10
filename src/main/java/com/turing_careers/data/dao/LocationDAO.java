package com.turing_careers.data.dao;

import com.turing_careers.data.DAO;
import com.turing_careers.data.model.Developer;
import com.turing_careers.data.model.Location;

import java.util.List;
import java.util.Optional;

public class LocationDAO extends DAO {

    private static LocationDAO instance;
    private LocationDAO() {
        super();
    }

    /**
     * @return Istanza condivisa da tutti i LocationDAO
     */
    public static synchronized LocationDAO getInstance() {
        if (instance == null)
            instance = new LocationDAO();

        return instance;
    }

    /**
     * @return Una lista contenente ogni istanza di Location
     */
    public List<Location> getLocations() {
        return Optional.of(
                super.em
                        .createNamedQuery("findAllLocation", Location.class)
                        .getResultList()
        ).orElse(null);
    }

    /**
     * @param lat La latitudine della Location da recuperare
     * @param lon La longitudine della location da recuperare
     * @return L'istanza di Location contenente lat e lon come coppia di latitudine e longitudine
     */
    public Developer getLocationByLatAndLon(String lat, String lon) {
        return Optional.of(
                super.em
                        .createNamedQuery("findLocationLatAndLog", Developer.class)
                        .setParameter("lat", lat)
                        .setParameter("lon", lon)
                        .getSingleResult()
        ).orElse(null);
    }

    /**
     * Aggiunge una Location al database
     * @param location L'istanza di Location da aggiungere
     * @throws PersistenceException Lanciata quando avviene un errore durante l'aggiunta
     */
    public void addLocation(Location location) throws PersistenceException {
        try {
            em.getTransaction().begin();
            em.persist(location);
            em.getTransaction().commit();
        } catch (Exception ex) { throw new PersistenceException(ex.getMessage()); }
    }

    /**
     * Rimuove una Location dal database
     * @param location L'istanza di Location da rimuovere
     * @throws PersistenceException Lanciata quando avviene un errore durante la rimozione
     */
    public void removeLocation(Location location) throws PersistenceException {
        try {
            em.getTransaction().begin();
            em.remove(em.merge(location));
            em.getTransaction().commit();
        } catch (Exception ex) { throw new PersistenceException(ex.getMessage()); }
    }

    /**
     * Aggiorna una Location nel database
     * @param location L'istanza di Location da aggiornare
     * @throws PersistenceException Lanciata quando avviene un errore durante l'aggiornamento
     */
    public void updateLocation(Location location) throws PersistenceException {
        try {
            em.getTransaction().begin();
            em.merge(location);
            em.getTransaction().commit();
        } catch (Exception ex) { throw new PersistenceException(ex.getMessage()); }
    }
}
