package unit;

import com.turing_careers.data.dao.EmployerDAO;
import com.turing_careers.data.dao.LocationDAO;
import com.turing_careers.data.dao.OfferDAO;
import com.turing_careers.data.dao.PersistenceException;
import com.turing_careers.data.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class TestOfferDAO {
    @Test
    public void addOfferTest() throws PersistenceException {
        OfferDAO offerDAO = OfferDAO.getInstance();
        EmployerDAO employerDAO = EmployerDAO.getInstance();
        LocationDAO locationDAO = LocationDAO.getInstance();
        List<Language> languages = new ArrayList<>();
        //languages.add(new Language("it_IT"));
        List<Skill> skills = new ArrayList<>();
        //skills.add(new Skill("Python", "Programming language"));
        Employer employer = new Employer("Antonio", "Pagnotta", "antoniopagnotta_figo@gmail.com", "123456", "TonyLoaf Inc.");
        Location location = new Location("Milano", 3223.0, 2332.0);
        Offer offer = new Offer("Sviluppatore Java full-stack", "Lavoro super pagato come sviluppatore Java full-stack.", "Aperta", "Remoto", employer, location, skills, languages);

        try {
            employerDAO.addEmployer(employer);
            locationDAO.addLocation(location);
            offerDAO.addOffer(offer);
        } catch (PersistenceException ex) {
            Assertions.fail();
        }

        List<Offer> offers = offerDAO.getOffers();
        Assertions.assertFalse(offers.isEmpty());
        System.out.println(offers);
    }
}
