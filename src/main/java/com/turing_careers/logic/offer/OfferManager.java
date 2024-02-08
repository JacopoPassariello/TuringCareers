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
    private OfferDAO offerDAO;

    public OfferManager() {
        this.offerDAO = OfferDAO.getInstance();
    }

    /**
     *
     */
    public void createOffer() throws Exception {

    }

    /**
     *
     */
    public void modifyOffer() throws Exception {

    }


    /**
     *
     */
    public void changeStatus(int offerId, String newState) throws Exception {
        if (newState.equals(OfferManager.STATE_CLOSED)) {
            // TODO: implement removeOfferByID
            Offer toRemove = this.offerDAO.getOfferById(offerId);
            this.offerDAO.removeOffer(toRemove);
        } else {
            Offer toUpdate = this.offerDAO.getOfferById(offerId);

            toUpdate.setState(newState);

            this.offerDAO.updateOffer(toUpdate);
        }
    }
}