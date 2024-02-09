package com.turing_careers.logic.offer;

import com.turing_careers.data.dao.OfferDAO;
import com.turing_careers.data.dao.PersistenceException;
import com.turing_careers.data.model.Developer;
import com.turing_careers.data.model.Offer;
import com.turing_careers.logic.validator.LanguageValidator;
import com.turing_careers.logic.validator.SkillValidator;
import com.turing_careers.logic.validator.ValidationException;

import java.text.ParseException;

/**
 *
 * */
public class OfferManager {

    public static void editOffer(Offer offer)  throws PersistenceException {
        OfferDAO updater = OfferDAO.getInstance();
        updater.updateOffer(offer);
    }

    public static void deleteOffer(Offer offer)  throws PersistenceException {
        OfferDAO updater = OfferDAO.getInstance();
        updater.removeOffer(offer);

    }

    public static void createOffer(Offer offer)  throws PersistenceException {
        OfferDAO updater = OfferDAO.getInstance();
        updater.addOffer(offer);
    }

    public static void subscribeToOffer(Developer dev, Offer offer) throws PersistenceException {
        OfferDAO updater = OfferDAO.getInstance();
        if (!offer.getSubscribedDevelopers().contains((dev))) {
            offer.getSubscribedDevelopers().add(dev);
            updater.updateOffer(offer);
        }
    }
}