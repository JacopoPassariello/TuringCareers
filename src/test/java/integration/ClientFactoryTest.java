package integration;

import com.turing_careers.data.model.*;
import com.turing_careers.domain.search.ClientFactory;
import com.turing_careers.domain.search.ClientType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ClientFactoryTest {
    @Test
    public void searchOffer() {
        // Setup Input
        Skill skill = new Skill("Python", "Programming Language");
        List<Skill> skills = new ArrayList<>();
        skills.add(skill);

        Language lang = new Language("it");
        List<Language> languages = new ArrayList<>();
        languages.add(lang);

        Developer dev = new Developer(
                1L,
                "Antonino",
                "Lorenzo",
                "bio",
                "anton@gmail.com",
                "1234",
                new Location(),
                skills,
                languages
        );

        // Execution
        List<Offer> offers = new ClientFactory()
                .setType(ClientType.OFFER)
                .getRecommenderEngine()
                .search("Web Developer", dev);

        Assertions.assertNotNull(offers);
        for (Offer offer : offers)
            System.out.println(offer);
    }
}
