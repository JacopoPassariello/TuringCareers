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

    public static synchronized LocationDAO getInstance() {
        if (instance == null)
            instance = new LocationDAO();

        return instance;
    }

    public List<Location> getLocation() {
        return Optional.of(
                super.em
                        .createNamedQuery("findAllLocation", Location.class)
                        .getResultList()
        ).orElse(null);
    }

    public Developer getLocationLatAndLog(String lat, String log) {
        return Optional.of(
                super.em
                        .createNamedQuery("findLocationLatAndLog", Developer.class)
                        .setParameter("lat", lat)
                        .setParameter("log", log)
                        .getSingleResult()
        ).orElse(null);
    }

    public void addLocation(Location location) throws Exception {
        try {
            em.getTransaction().begin();
            em.persist(location);
            em.getTransaction().commit();
        } catch (Exception ex) { throw new Exception(ex); }
    }

    public void removeLocation(Location location) throws Exception {
        try {
            em.getTransaction().begin();
            em.remove(em.merge(location));
            em.getTransaction().commit();
        } catch (Exception ex) { throw new Exception(ex); }
    }

    public void updateLocation(Location location) throws Exception {
        try {
            em.getTransaction().begin();
            em.merge(location);
            em.getTransaction().commit();
        } catch (Exception ex) { throw new Exception(ex); }
    }
}
