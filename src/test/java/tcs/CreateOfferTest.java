package tcs;

import com.turing_careers.data.dao.EmployerDAO;
import com.turing_careers.data.dao.LanguageDAO;
import com.turing_careers.data.dao.OfferDAO;
import com.turing_careers.data.model.*;
import com.turing_careers.logic.offer.OfferManager;
import com.turing_careers.logic.validator.ValidationException;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CreateOfferTest {
    private List<Skill> correctSkills;
    private List<Skill> incorrectSkills;
    private Location correctLocation;
    private Location incorrectLocation;
    private List<Language> correctLanguages;
    private List<Language> incorrectLanguages;
    private Employer employer;

    @BeforeAll
    public void prepare() {
        LanguageDAO ldao = LanguageDAO.getInstance();
        EmployerDAO edao = EmployerDAO.getInstance();

        this.correctSkills = new ArrayList<>(List.of(
                new Skill("Java", "Programming Language"),
                new Skill("JavaScript", "Programming Language"),
                new Skill("HTML", "Programming Language")
        ));

        this.incorrectSkills = new ArrayList<>(List.of(
                new Skill("Java", "Programming Language"),
                new Skill("JavaScript", "Programming Language"),
                new Skill("Metallurgia", "Non Valida")
        ));

        this.correctLocation = new Location("83024 Monteforte Irpino AV, Italia", 1d, 2d);
        this.incorrectLocation = new Location("Marina di Avellino, AV", 5d, 5d);

        this.correctLanguages = new ArrayList<>();
        correctLanguages.add(ldao.getLanguageByLanguageCode("it_IT"));
        correctLanguages.add(ldao.getLanguageByLanguageCode("en_UK"));

        this.incorrectLanguages = new ArrayList<>(List.of(new Language("ks_WO")));

        this.employer = edao.getEmployerById(1L);
    }
    @Test
    @Order(1)
    public void titleLengthError() {
        Offer offer = new Offer(
                "Lavoro",
                "Cercasi programmatori per lavorare allo sviluppo della APP dei miei sogni",
                Offer.STATE_OPEN,
                Offer.ON_SITE,
                this.employer,
                this.correctLocation,
                this.correctSkills,
                this.correctLanguages
        );

        Assertions.assertThrows(
                ValidationException.class,
                () -> OfferManager.createOffer(offer)
        );
    }

    @Test
    @Order(2)
    public void descriptionLengthError() {
        Offer offer = new Offer(
                "Mobile APP",
                "Serve informatico bravo",
                Offer.STATE_OPEN,
                Offer.ON_SITE,
                this.employer,
                this.correctLocation,
                this.correctSkills,
                this.correctLanguages
        );

        Assertions.assertThrows(
                ValidationException.class,
                () -> OfferManager.createOffer(offer)
        );
    }

    @Test
    @Order(3)
    public void locationNotNullError() {
        Offer offer = new Offer(
                "Mobile APP",
                "Cercasi programmatori per lavorare allo sviluppo della APP dei miei sogni",
                Offer.STATE_OPEN,
                Offer.ON_SITE,
                this.employer,
                null,
                this.correctSkills,
                this.correctLanguages
        );

        Assertions.assertThrows(
                ValidationException.class,
                () -> OfferManager.createOffer(offer)
        );
    }

    @Test
    @Order(4)
    public void locationNotValidError() {
        Offer offer = new Offer(
                "Mobile APP",
                "Cercasi programmatori per lavorare allo sviluppo della APP dei miei sogni",
                Offer.STATE_OPEN,
                Offer.ON_SITE,
                this.employer,
                this.incorrectLocation,
                this.correctSkills,
                this.correctLanguages
        );

        Assertions.assertThrows(
                ValidationException.class,
                () -> OfferManager.createOffer(offer)
        );
    }

    @Test
    @Order(5)
    public void skillsNotEmptyError() {
        Offer offer = new Offer(
                "Mobile APP",
                "Cercasi programmatori per lavorare allo sviluppo della APP dei miei sogni",
                Offer.STATE_OPEN,
                Offer.ON_SITE,
                this.employer,
                this.correctLocation,
                new ArrayList<>(),
                this.correctLanguages
        );

        Assertions.assertThrows(
                ValidationException.class,
                () -> OfferManager.createOffer(offer)
        );
    }

    @Test
    @Order(6)
    public void skillsNotValidError() {
        Offer offer = new Offer(
                "Mobile APP",
                "Cercasi programmatori per lavorare allo sviluppo della APP dei miei sogni",
                Offer.STATE_OPEN,
                Offer.ON_SITE,
                this.employer,
                this.correctLocation,
                this.incorrectSkills,
                this.correctLanguages
        );

        Assertions.assertThrows(
                ValidationException.class,
                () -> OfferManager.createOffer(offer)
        );
    }

    @Test
    @Order(7)
    public void languagesNotEmptyError() {
        Offer offer = new Offer(
                "Mobile APP",
                "Cercasi programmatori per lavorare allo sviluppo della APP dei miei sogni",
                Offer.STATE_OPEN,
                Offer.ON_SITE,
                this.employer,
                this.correctLocation,
                this.correctSkills,
                new ArrayList<>()
        );

        Assertions.assertThrows(
                ValidationException.class,
                () -> OfferManager.createOffer(offer)
        );
    }

    @Test
    @Order(8)
    public void languagesNotValidError() {
        Offer offer = new Offer(
                "Mobile APP",
                "Cercasi programmatori per lavorare allo sviluppo della APP dei miei sogni",
                Offer.STATE_OPEN,
                Offer.ON_SITE,
                this.employer,
                this.correctLocation,
                this.correctSkills,
                this.incorrectLanguages
        );

        Assertions.assertThrows(
                ValidationException.class,
                () -> OfferManager.createOffer(offer)
        );
    }

    @Test
    @Order(9)
    public void success() {
        Offer offer = new Offer(
                "Mobile APP",
                "Cercasi programmatori per lavorare allo sviluppo della APP dei miei sogni",
                Offer.STATE_OPEN,
                Offer.ON_SITE,
                this.employer,
                this.correctLocation,
                this.correctSkills,
                this.correctLanguages
        );
        System.out.println(correctLocation.getName());
        Assertions.assertDoesNotThrow(
                () -> OfferManager.createOffer(offer),
                "Exception thrown"
        );
    }
}
