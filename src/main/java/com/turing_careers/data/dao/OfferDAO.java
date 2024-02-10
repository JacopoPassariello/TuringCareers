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

    /**
     * @return Istanza condivisa da tutti gli OfferDAO
     */
    public static synchronized OfferDAO getInstance() {
        if (instance == null)
            instance = new OfferDAO();

        return instance;
    }

    /**
     * @return Una lista contenente ogni istanza di Offer
     */
    public List<Offer> getOffers() {
        return Optional.of(
                super.em
                        .createNamedQuery("findAllOffers", Offer.class)
                        .getResultList()
        ).orElse(null);
    }

    /**
     * @param id L'id da usare per recuperare la Offer
     * @return L'istanza di Offer che contiene id come id
     */
    public Offer getOfferById(Long id) {
        return Optional.of(
                super.em
                        .createNamedQuery("findOfferById", Offer.class)
                        .setParameter("id", id)
                        .getSingleResult()
        ).orElse(null);
    }

    /**
     * Aggiunge una Offer al database
     * @param offer L'istanza di Offer da aggiungere
     * @throws PersistenceException Lanciata quando avviene un errore durante l'aggiunta
     */
    public void addOffer(Offer offer) throws PersistenceException {
        try {
            em.getTransaction().begin();
            em.persist(offer);
            em.getTransaction().commit();
        } catch (Exception ex) { throw new PersistenceException(ex.getMessage()); }
    }

    /**
     * Rimuove una Offer dal database
     * @param offer L'istanza di Offer da rimuovere
     * @throws PersistenceException Lanciata quando avviene un errore durante la rimozione
     */
    public void removeOffer(Offer offer) throws PersistenceException {
        try {
            em.getTransaction().begin();
            em.remove(em.merge(offer));
            em.getTransaction().commit();
        } catch (Exception ex) { throw new PersistenceException(ex.getMessage()); }
    }

    /**
     * Aggiorna una Offer dal database
     * @param offer L'istanza di Offer da aggiornare
     * @throws PersistenceException Lanciata quando avviene un errore durante l'aggiornamento
     */
    public void updateOffer(Offer offer) throws PersistenceException {
        try {
            em.getTransaction().begin();
            em.merge(offer);
            em.getTransaction().commit();
        } catch (Exception ex) { throw new PersistenceException(ex.getMessage()); }
    }
}
