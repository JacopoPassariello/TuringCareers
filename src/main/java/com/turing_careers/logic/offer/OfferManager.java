package com.turing_careers.logic.offer;

import com.turing_careers.data.dao.OfferDAO;
import com.turing_careers.data.model.Developer;
import com.turing_careers.data.model.Offer;
import com.turing_careers.logic.validator.LanguageValidator;
import com.turing_careers.logic.validator.SkillValidator;
import com.turing_careers.logic.validator.ValidationException;

/**
 *
 * */
public class OfferManager {

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
}