package com.turing_careers.logic.service.offer;

import com.turing_careers.data.model.Offer;
import com.turing_careers.logic.service.utils.LanguageValidator;
import com.turing_careers.logic.service.utils.SkillValidator;
import com.turing_careers.logic.service.utils.ValidationException;

/**
 * Classe che implementa la validazione di un oggetto Offer.
 */
public class OfferValidator {
    /**
     * @author Claudio Gaudino, Jacopo Passariello
     * @param offer Entità da validare.
     * @throws ValidationException Lanciata quando l'entità contiene almeno un campo contenente un valore non valido.
     */
    public static void checkValidity(Offer offer) throws ValidationException {
        if (offer.getLocationType() == null) {
            throw new ValidationException("Offer input is not valid!");
        }
        if (offer.getLocationType().equals(Offer.ON_SITE)) {
            if (offer.getLocation() == null || offer.getLocation().isEmpty()) {
                throw new ValidationException("Offer input is not valid!");
            }
        }
        if (offer == null
                || offer.getTitle().length() < 8
                || offer.getTitle().length() > 64
                /*|| offer.getDescription().length() < 32 */
                || offer.getDescription().length() > 5120
                || offer.getSkills().size() == 0
                || offer.getLanguages().size() == 0
                || offer.getLocationType().isEmpty()
                || offer.getEmployer() == null
        ) throw new ValidationException("Offer input is not valid!");

        //CHECKME: blocco di codice per validazione di skill language
        SkillValidator.validateSkills(offer.getSkills());
        LanguageValidator.validateLanguages(offer.getLanguages());
    }
}
