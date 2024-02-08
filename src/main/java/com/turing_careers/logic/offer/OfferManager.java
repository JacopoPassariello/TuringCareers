package com.turing_careers.logic.offer;

import com.turing_careers.data.dao.OfferDAO;
import com.turing_careers.data.model.Offer;

/**
 *
 * */
public class OfferManager {
    private static String STATE_OPEN = "OPEN";
    private static String STATE_PAUSED = "PAUSED";
    private static String STATE_CLOSED = "CLOSED";

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

    public static void saveOffer(Offer offer)  throws UpdateOfferException {
        OfferDAO updater = OfferDAO.getInstance();
        try {
            updater.addOffer(offer);
        } catch (Exception e) {
            throw new UpdateOfferException(e.getMessage());
        }
    }

    public void changeStatus(int offerId, String newState) throws Exception {
        OfferDAO updater = OfferDAO.getInstance();
        if (newState.equals(OfferManager.STATE_CLOSED)) {
            // TODO: implement removeOfferByID
            Offer toRemove = updater.getOfferById(offerId);
            updater.removeOffer(toRemove);
        } else {
            Offer toUpdate = updater.getOfferById(offerId);

            toUpdate.setState(newState);

            updater.updateOffer(toUpdate);
        }
    }
}