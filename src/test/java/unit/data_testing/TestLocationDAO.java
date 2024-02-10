package unit.data_testing;

import com.turing_careers.data.dao.LocationDAO;
import com.turing_careers.data.dao.PersistenceException;
import com.turing_careers.data.dao.SkillDAO;
import com.turing_careers.data.model.Location;
import com.turing_careers.data.model.Skill;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ParameterResolutionException;

import java.util.List;

public class TestLocationDAO {

    @Test
    public void addLocationTest() throws ParameterResolutionException {
        LocationDAO locationDAO = LocationDAO.getInstance();
        Location location = new Location("Milan", 3455.0, 5675.0);

        try {
            locationDAO.addLocation(location);
        } catch (PersistenceException ex) {
            Assertions.fail();
        }

        List<Location> locations = locationDAO.getLocations();
        Assertions.assertFalse(locations.isEmpty());
        System.out.println(locations);
    }
}
