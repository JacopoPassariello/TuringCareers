package unit;

import com.turing_careers.data.dao.DeveloperDAO;
import com.turing_careers.data.model.Developer;
import com.turing_careers.data.model.Language;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TestDeveloperDAO {
    @Test
    public void addDeveloperTest() throws Exception {
        DeveloperDAO developerDAO = DeveloperDAO.getInstance();
        //Developer developer = new Developer("Antonio", "Pagnotta", "Java Developer", "antoniopagnottafigo@gmail.com", );

        try {
            //developerDAO.addDeveloper(developer);
        } catch (Exception ex) {
            Assertions.fail();
        }

        List<Developer> developers = developerDAO.getDevelopers();
        Assertions.assertFalse(developers.isEmpty());
        System.out.println(developers);
    }
}
