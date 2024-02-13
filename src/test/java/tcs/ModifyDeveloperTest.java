package tcs;

import com.turing_careers.data.dao.LanguageDAO;
import com.turing_careers.data.model.*;
import com.turing_careers.logic.service.user.UserManager;
import com.turing_careers.logic.service.utils.ValidationException;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ModifyDeveloperTest {
    private String correctName;
    private String incorrectName;
    private String correctSurname;
    private String incorrectSurname;
    private String correctBio;
    private String incorrectBio;
    private List<Skill> correctSkills;
    private List<Skill> incorrectSkills;
    private List<Language> correctLanguages;
    private List<Language> incorrectLanguages;

    //Non-tested variables.
    private String mail;
    private String password;
    private String location;


    @BeforeAll
    public void prepare() {
        LanguageDAO languageDAO = LanguageDAO.getInstance();
        this.correctName = "Franco";
        this.incorrectName = "";
        this.correctSurname = "Franchi";
        this.incorrectSurname = "";
        this.correctBio = "Esperto di programmazione di sistemi embedded e bare metal";
        this.incorrectBio = "";
        for(int i = 0; i <= 2048; i++) {
            this.incorrectBio = this.incorrectBio + "A";
        }
        this.correctSkills = new ArrayList<>(List.of(
                new Skill("C", "Programming Language"),
                new Skill("C++", "Programming Language"),
                new Skill("Rust", "Programming Language")
        ));

        this.incorrectSkills = new ArrayList<>(List.of(
                new Skill("C", "Programming Language"),
                new Skill("C++", "Programming Language"),
                new Skill("Metallurgia", "Non Valida")
        ));

        this.correctLanguages = new ArrayList<>();
        correctLanguages.add(languageDAO.getLanguageByLanguageCode("it_IT"));
        correctLanguages.add(languageDAO.getLanguageByLanguageCode("en_UK"));

        this.incorrectLanguages = new ArrayList<>(List.of(new Language("ks_WO")));

        this.location = "Napoli";
        this.mail = "francofranchi@gmail.com";
        this.password = "francofranchi123_";


    }
    @Test
    @Order(1)
    public void nameLengthError() {
        Developer developer = new Developer(
                this.incorrectName,
                this.correctSurname,
                this.correctBio,
                this.mail,
                this.password,
                this.location,
                this.correctSkills,
                this.correctLanguages
        );
        Assertions.assertThrows(
                ValidationException.class,
                () -> UserManager.createProfile(developer)
        );
    }
    @Test
    @Order(2)
    public void surnameLengthError() {
        Developer developer = new Developer(
                this.correctName,
                this.incorrectSurname,
                this.correctBio,
                this.mail,
                this.password,
                this.location,
                this.correctSkills,
                this.correctLanguages
        );
        Assertions.assertThrows(
                ValidationException.class,
                () -> UserManager.createProfile(developer)
        );
    }
    @Test
    @Order(3)
    public void bioLengthError() {
        Developer developer = new Developer(
                this.correctName,
                this.correctSurname,
                this.incorrectBio,
                this.mail,
                this.password,
                this.location,
                this.correctSkills,
                this.correctLanguages
        );
        Assertions.assertThrows(
                ValidationException.class,
                () -> UserManager.createProfile(developer)
        );
    }
    @Test
    @Order(4)
    public void skillNotEmptyError() {

        Developer developer = new Developer(
                this.correctName,
                this.correctSurname,
                this.correctBio,
                this.mail,
                this.password,
                this.location,
                new ArrayList<Skill>(),
                this.correctLanguages
        );
        Assertions.assertThrows(
                ValidationException.class,
                () -> UserManager.createProfile(developer)
        );
    }
    @Test
    @Order(5)
    public void skillNotValidError() {
        Developer developer = new Developer(
                this.correctName,
                this.correctSurname,
                this.correctBio,
                this.mail,
                this.password,
                this.location,
                this.incorrectSkills,
                this.correctLanguages
        );
        Assertions.assertThrows(
                ValidationException.class,
                () -> UserManager.createProfile(developer)
        );
    }
    @Test
    @Order(6)
    public void languageNotEmptyError() {
        Developer developer = new Developer(
                this.correctName,
                this.correctSurname,
                this.correctBio,
                this.mail,
                this.password,
                this.location,
                this.correctSkills,
                new ArrayList<Language>()
        );
        Assertions.assertThrows(
                ValidationException.class,
                () -> UserManager.createProfile(developer)
        );
    }
    @Test
    @Order(6)
    public void languageNotValidError() {
        Developer developer = new Developer(
                this.correctName,
                this.correctSurname,
                this.correctBio,
                this.mail,
                this.password,
                this.location,
                this.correctSkills,
                this.incorrectLanguages
        );
        Assertions.assertThrows(
                ValidationException.class,
                () -> UserManager.createProfile(developer)
        );
    }
    @Test
    @Order(7)
    public void success() {
        Developer developer = new Developer(
                this.correctName,
                this.correctSurname,
                this.correctBio,
                this.mail,
                this.password,
                this.location,
                this.correctSkills,
                this.correctLanguages
        );
        Assertions.assertDoesNotThrow(
                () -> UserManager.createProfile(developer),
                "Exception thrown"
        );
        
    }

}
