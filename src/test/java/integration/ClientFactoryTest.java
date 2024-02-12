package integration;

import com.turing_careers.data.model.*;
import com.turing_careers.logic.search.ClientFactory;
import com.turing_careers.logic.search.ClientType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ClientFactoryTest {
    @Test
    public void searchOffer() {
        // Setup Input
        Skill skill1 = new Skill("Python", "Programming Language");
        skill1.setSkillId(3L);
        Skill skill2 = new Skill("MySQL", "Database");
        skill2.setSkillId(150L);
        Skill skill3 = new Skill("C", "Programming Language");
        skill3.setSkillId(10L);
        Skill skill4 = new Skill("Pandas", "Framework_2");
        skill4.setSkillId(60L);
        Skill skill5 = new Skill("TensorFlow", "Framework_2");
        skill5.setSkillId(60L);
        Skill skill6 = new Skill("PyTorch", "Framework_2");
        skill6.setSkillId(68L);

        List<Skill> skills = new ArrayList<>();
        skills.add(skill1);
        skills.add(skill2);
        skills.add(skill3);
        skills.add(skill4);
        skills.add(skill5);
        skills.add(skill6);

        Language lang = new Language("it");
        List<Language> languages = new ArrayList<>();
        languages.add(lang);

        String devLoc = "Roma";

        Developer dev = new Developer(
                1L,
                "Antonino",
                "Lorenzo",
                "bio",
                "anton@gmail.com",
                "1234",
                devLoc,
                skills,
                languages,
                new ArrayList<Offer>()
        );

        // Execution
        List<Offer> offers = new ClientFactory()
                .setType(ClientType.OFFER)
                .getRecommenderEngine()
                .search("Web Developer", dev);

        Assertions.assertNotNull(offers);
        for (Offer offer : offers)
            System.out.println(offer.getTitle());
    }
}
