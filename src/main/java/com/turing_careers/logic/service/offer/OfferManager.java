package com.turing_careers.logic.service.offer;

import com.turing_careers.data.dao.OfferDAO;
import com.turing_careers.data.dao.PersistenceException;
import com.turing_careers.data.model.Developer;
import com.turing_careers.data.model.Offer;
import com.turing_careers.logic.service.user.UserValidator;
import com.turing_careers.logic.service.utils.ValidationException;


public class OfferManager {

    /**
     * Permette la modifica di una offerta, verificando la correttezza dei nuovi valori
     * @param offer L'offerta modificata
     * @throws PersistenceException Lanciata quando avviene un errore durante l'aggiornamento dell'offerta nel database
     * @throws ValidationException Lanciata quando almeno uno dei campi di offer non è valido
     */
    public static void editOffer(Offer offer)  throws PersistenceException, ValidationException {
        OfferValidator.checkValidity(offer);
        OfferDAO updater = OfferDAO.getInstance();
        updater.updateOffer(offer);
    }

    /**
     * Permette la rimozione di una offerta
     * @param offer L'offerta da rimuovere
     * @throws PersistenceException Lanciata quando avviene un errore durante la rimozione dell'offerta dal database
     */
    public static void deleteOffer(Offer offer)  throws PersistenceException {
        OfferDAO updater = OfferDAO.getInstance();
        updater.removeOffer(offer);

    }

    /**
     * Permette la creazione di una offerta
     * @param offer La nuova offerta
     * @throws PersistenceException Lanciata quando avviene un errore durante l'aggiunta dell'offerta al database
     * @throws ValidationException Lanciata quando almeno uno dei campi di offer non è valido
     */
    public static void createOffer(Offer offer)  throws PersistenceException, ValidationException {
        OfferValidator.checkValidity(offer);
        OfferDAO updater = OfferDAO.getInstance();
        updater.addOffer(offer);
    }

    /**
     * Permette ad un Developer di iscriversi ad una Offer
     * @param dev Il Developer che esegue l'operazione
     * @param offer La Offer a cui dev intende iscriversi
     * @throws PersistenceException Lanciata quando avviene un errore durante l'assegnazione della relazione di iscrizione tra dev e offer
     * @throws ValidationException Lanciata quando almeno uno dei campi di dev o offer non è valido
     */
    public static void subscribeToOffer(Developer dev, Offer offer) throws PersistenceException, ValidationException {
        OfferValidator.checkValidity(offer);
        UserValidator.checkValidity(dev);
        OfferDAO updater = OfferDAO.getInstance();
        if (!offer.getSubscribedDevelopers().contains((dev))) {
            offer.getSubscribedDevelopers().add(dev);
            updater.updateOffer(offer);
        }
    }

    /**
     * // TODO: remove from OfferManager, it is not specified by Interfaces,
     * // TODO: put in UserManager, also getEmployerOffers is implemented there
     * Restituisce una Offer
     * @param offerId L'id della Offer da restituire
     * @return La Offer che ha offerId come id
     */
    public static Offer getOffer(Long offerId) {
        OfferDAO retriever = OfferDAO.getInstance();
        return retriever.getOfferById(offerId);
    }
}