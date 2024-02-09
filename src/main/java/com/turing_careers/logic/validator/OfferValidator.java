package com.turing_careers.logic.validator;

import com.turing_careers.data.model.Offer;
import com.turing_careers.logic.offer.OfferManager;
import com.turing_careers.logic.offer.OfferNotValidException;
/**
 * Classe che implementa la validazione di un oggetto Offer.
 */
public class OfferValidator {
    /**
     * @author Claudio Gaudino, Jacopo Passariello
     * @param offer Entità da validare.
     * @throws ValidationException Lanciata quando l'entità contiene almeno un campo contenente un valore non valido.
     */
    private static void checkValidity(Offer offer) throws ValidationException {
        if (offer.getDescription().isEmpty()
                || offer.getTitle().isEmpty()
                || offer.getSkills().size() == 0
                || offer.getLanguages().size() == 0
                || offer.getLocationType().isEmpty()
                || offer.getEmployer() == null
                || offer.getLocationType().equals(Offer.IN_PLACE) && offer.getLocation() == null
        ) throw new ValidationException("Offer input is not valid!");

        //CHECKME: blocco di codice per validazione di skill language
        SkillValidator.validateSkills(offer.getSkills());
        LanguageValidator.validateLanguages(offer.getLanguages());
        }
}
