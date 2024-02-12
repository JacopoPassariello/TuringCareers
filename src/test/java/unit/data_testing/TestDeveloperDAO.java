package unit.data_testing;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.turing_careers.data.dao.DeveloperDAO;
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
        Location location = new Location("Milano", 323.0, 232.0);
        List<Skill> skills = new ArrayList<>();
        List<Language> languages = new ArrayList<>();
        List<Offer> offers = new ArrayList<>();
        Developer developer = new Developer("Antonio", "Pagnotta", "Java Developer", "antoniopagnottafigo@gmail.com", "123456", location, skills, languages);

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

    @Test
    public void fromJSON() {
        String jsonString = "{\"_Developer__f_name\":\"Antonino\",\"_Developer__l_name\":\"Lorenzo\",\"_Developer__bio\":\"\",\"_Developer__mail\":\"antonino.lorenzo02@gmail.com\",\"_Developer__psw\":\"DioCane002\",\"_Developer__location\":{\"_Location__id\":1,\"_Location__name\":\"Roma\",\"_Location__lat\":0,\"_Location__lon\":0},\"_Developer__skills\":[{\"_Skill_id\":1,\"_Skill__name\":\"Python\",\"_Skill__type\":\"Programming Language\"}],\"_Developer__languages\":[{\"_Language__id\":1,\"_Language__code\":\"it\"}]}\n";
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(
                DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                false
        );
        try {
            Developer dev = objectMapper.readValue(jsonString, Developer.class);
            System.out.println(dev);
        } catch (JsonProcessingException err) {
            System.out.println("Error");
        }
    }
}
