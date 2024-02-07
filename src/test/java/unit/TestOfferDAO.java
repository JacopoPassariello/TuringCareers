package unit;

import com.turing_careers.data.dao.LanguageDAO;
import com.turing_careers.data.model.Language;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TestOfferDAO {
    @Test
    public void addOfferTest() throws Exception {
        LanguageDAO languageDAO = LanguageDAO.getInstance();
        Language language = new Language("it_IT");

        try {
            languageDAO.addLanguage(language);
        } catch (Exception ex) {
            Assertions.fail();
        }

        List<Language> languages = languageDAO.getLanguages();
        Assertions.assertFalse(languages.isEmpty());
        System.out.println(languages);
    }
}
