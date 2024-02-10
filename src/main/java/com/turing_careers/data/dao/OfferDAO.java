package com.turing_careers.data.dao;

import com.turing_careers.data.DAO;
import com.turing_careers.data.model.Offer;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

public class OfferDAO extends DAO {

    private static OfferDAO instance;
    private OfferDAO() {
        super();
    }

    public static synchronized OfferDAO getInstance() {
        if (instance == null)
            instance = new OfferDAO();

        return instance;
    }

    public List<Offer> getOffers() {
        return Optional.of(
                super.em
                        .createNamedQuery("findAllOffers", Offer.class)
                        .getResultList()
        ).orElse(null);
    }

    public Offer getOfferById(Long id) {
        return Optional.of(
                super.em
                        .createNamedQuery("findOfferById", Offer.class)
                        .setParameter("id", id)
                        .getSingleResult()
        ).orElse(null);
    }

    public void addOffer(Offer offer) throws PersistenceException {
        try {
            em.getTransaction().begin();
            em.persist(offer);
            em.getTransaction().commit();
        } catch (Exception ex) { throw new PersistenceException(ex.getMessage()); }
    }

    public void removeOffer(Offer offer) throws PersistenceException {
        try {
            em.getTransaction().begin();
            em.remove(em.merge(offer));
            em.getTransaction().commit();
        } catch (Exception ex) { throw new PersistenceException(ex.getMessage()); }
    }

    public void updateOffer(Offer offer) throws PersistenceException {
        try {
            em.getTransaction().begin();
            em.merge(offer);
            em.getTransaction().commit();
        } catch (Exception ex) { throw new PersistenceException(ex.getMessage()); }
    }
}
