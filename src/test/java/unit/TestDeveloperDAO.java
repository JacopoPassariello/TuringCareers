package unit;

import com.turing_careers.data.dao.DeveloperDAO;
import com.turing_careers.data.dao.LocationDAO;
import com.turing_careers.data.dao.PersistenceException;
import com.turing_careers.data.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class TestDeveloperDAO {
    @Test
    public void addDeveloperTest() {
        DeveloperDAO developerDAO = DeveloperDAO.getInstance();
        LocationDAO locationDAO = LocationDAO.getInstance();
        Location location = new Location("Milano", "323", "232");
        List<Skill> skills = new ArrayList<>();
        List<Language> languages = new ArrayList<>();
        List<Offer> offers = new ArrayList<>();
        Developer developer = new Developer("Antonio", "Pagnotta", "Java Developer", "antoniopagnottafigo@gmail.com", "123456", location, skills, languages, offers);

        try {
            locationDAO.addLocation(location);
            developerDAO.addDeveloper(developer);
        } catch (PersistenceException ex) {
            Assertions.fail();
        }

        List<Developer> developers = developerDAO.getDevelopers();
        Assertions.assertFalse(developers.isEmpty());
        System.out.println(developers);
    }
}
