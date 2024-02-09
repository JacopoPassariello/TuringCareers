package unit;

import com.turing_careers.data.dao.LanguageDAO;
import com.turing_careers.data.dao.LocationDAO;
import com.turing_careers.data.dao.PersistenceException;
import com.turing_careers.data.model.Language;
import com.turing_careers.data.model.Location;
import org.glassfish.jersey.message.internal.LanguageTag;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TestLanguageDAO {
    @Test
    public void addLanguageTest() throws Exception {
        LanguageDAO languageDAO = LanguageDAO.getInstance();
        Language language = new Language("it_IT");

        try {
            languageDAO.addLanguage(language);
        } catch (PersistenceException ex) {
            Assertions.fail();
        }

        List<Language> languages = languageDAO.getLanguages();
        Assertions.assertFalse(languages.isEmpty());
        System.out.println(languages);
    }
}
