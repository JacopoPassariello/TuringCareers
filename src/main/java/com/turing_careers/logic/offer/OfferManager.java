package com.turing_careers.logic.offer;

import com.turing_careers.data.dao.OfferDAO;
import com.turing_careers.data.model.Developer;
import com.turing_careers.data.model.Offer;

/**
 *
 * */
public class OfferManager {
    private static String STATE_OPEN = "OPEN";
    private static String STATE_PAUSED = "PAUSED";
    private static String STATE_CLOSED = "CLOSED";
    private static String IN_PLACE = "IN_PLACE";
    private static String REMOTE = "REMOTE";

    public static void editOffer(Offer offer)  throws UpdateOfferException {
        OfferDAO updater = OfferDAO.getInstance();
        try {
            updater.updateOffer(offer);
        } catch (Exception e) {
            throw new UpdateOfferException(e.getMessage());
        }
    }

    public static void deleteOffer(Offer offer)  throws UpdateOfferException {
        OfferDAO updater = OfferDAO.getInstance();
        try {
            updater.removeOffer(offer);
        } catch (Exception e) {
            throw new UpdateOfferException(e.getMessage());
        }
    }

    public static void createOffer(Offer offer)  throws UpdateOfferException {
        OfferDAO updater = OfferDAO.getInstance();
        try {
            updater.addOffer(offer);
        } catch (Exception e) {
            throw new UpdateOfferException(e.getMessage());
        }
    }

    public static void subscribeToOffer(Developer dev, Offer offer) throws UpdateOfferException {
        OfferDAO updater = OfferDAO.getInstance();
        try {
            if (!offer.getSubscribedDevelopers().contains((dev))) {
                offer.getSubscribedDevelopers().add(dev);
            }
            updater.updateOffer(offer);
        } catch (Exception e) {
            throw new UpdateOfferException(e.getMessage());
        }
    }

    public static void checkValidity(Offer offer) throws OfferNotValidException{
        if (offer.getDescription().isEmpty()
                || offer.getTitle().isEmpty()
                || offer.getSkills().size() == 0
                || offer.getLanguages().size() == 0
                || offer.getLocationType().isEmpty()
                || offer.getEmployer() == null
                || offer.getLocationType() == OfferManager.IN_PLACE && offer.getLocation() == null
        ) throw new OfferNotValidException("Invalid Offer Input!");
    }
}