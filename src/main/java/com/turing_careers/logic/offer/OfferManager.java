package com.turing_careers.logic.offer;

import com.turing_careers.data.dao.OfferDAO;
import com.turing_careers.data.dao.PersistenceException;
import com.turing_careers.data.model.Developer;
import com.turing_careers.data.model.Offer;
import com.turing_careers.data.model.User;
import com.turing_careers.logic.validator.OfferValidator;
import com.turing_careers.logic.validator.UserValidator;
import com.turing_careers.logic.validator.ValidationException;

import java.util.List;


/**
 *
 * */
public class OfferManager {

    public static void editOffer(Offer offer)  throws PersistenceException, ValidationException {
        OfferValidator.checkValidity(offer);
        OfferDAO updater = OfferDAO.getInstance();
        updater.updateOffer(offer);
    }

    public static void deleteOffer(Offer offer)  throws PersistenceException {
        OfferDAO updater = OfferDAO.getInstance();
        updater.removeOffer(offer);

    }

    public static void createOffer(Offer offer)  throws PersistenceException, ValidationException {
        OfferValidator.checkValidity(offer);
        OfferDAO updater = OfferDAO.getInstance();
        updater.addOffer(offer);
    }

    public static void subscribeToOffer(Developer dev, Offer offer) throws PersistenceException, ValidationException {
        OfferValidator.checkValidity(offer);
        UserValidator.checkValidity(dev);
        OfferDAO updater = OfferDAO.getInstance();
        if (!offer.getSubscribedDevelopers().contains((dev))) {
            offer.getSubscribedDevelopers().add(dev);
            updater.updateOffer(offer);
        }
    }

    public static Offer getOffer(Long offerId) {
        OfferDAO retriever = OfferDAO.getInstance();
        return retriever.getOfferById(offerId);
    }
}