package tcs;

import com.turing_careers.data.model.*;
import com.turing_careers.logic.search.ApiClient;
import com.turing_careers.logic.search.ClientFactory;
import com.turing_careers.logic.search.ClientType;
import com.turing_careers.logic.search.RecommenderEngine;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.mockito.Mockito;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Implementation of TCS_RS_1, see Test Case Specification.
 * */
@TestMethodOrder(OrderAnnotation.class)
public class SearchOfferTest {

    private static RecommenderEngine searchOfferEngine;
    private static ApiClient client;
    private static String clientReturn;
    private static String query = "WebDeveloper";
    private static String nnQuery = "";
    private static Developer devCorrect;
    private static List<Language> languageList;

    @BeforeAll
    public static void setup() {
        // ApiClient stub setup
        client = Mockito.mock(ApiClient.class);

        // create RecommenderEngine
        searchOfferEngine = new ClientFactory()
                .setType(ClientType.OFFER)
                .getRecommenderEngine();
        searchOfferEngine.setClient(client);

        // Objects created here to reduce boilerplate in tests
        clientReturn = "[{\"_Offer__id\":1,\"_Offer__title\":\"Web Developer\",\"_Offer__state\":\"active\"," +
                "\"_Offer__description\":\"desc\",\"_Offer__employer\":{\"_Employer__id\":1,\"_Employer__f_name\":" +
                "\"Antonino\",\"_Employer__l_name\":\"Lorenzo\",\"_Employer__mail\":\"anton@gmail.com\",\"_Employer" +
                "__psw\":\"S3cur3Pas@s\",\"_Employer__company\":\"asd\"},\"_Offer__location_type\":\"Remote\",\"_Of" +
                "fer__location\":{\"_Location__id\":1,\"_Location__name\":\"Avellino Italia\",\"_Location__lat\":12" +
                "3,\"_Location__lon\":124},\"_Offer__skills\":[{\"_Skill__id\":1,\"_Skill__name\":\"Python\",\"_Ski" +
                "ll__type\":\"Programming Language\"}],\"_Offer__languages\":[{\"_Language__id\":1,\"_Language__cod" +
                "e\":\"it\"}]},{\"_Offer__id\":1,\"_Offer__title\":\"Web Developer\",\"_Offer__state\":\"active\",\"" +
                "_Offer__description\":\"desc\",\"_Offer__employer\":{\"_Employer__id\":1,\"_Employer__f_name\":\"An" +
                "tonino\",\"_Employer__l_name\":\"Lorenzo\",\"_Employer__mail\":\"anton@gmail.com\",\"_Employer__psw" +
                "\":\"S3cur3Pas@s\",\"_Employer__company\":\"asd\"},\"_Offer__location_type\":\"Remote\",\"_Offer__l" +
                "ocation\":{\"_Location__id\":1,\"_Location__name\":\"Avellino Italia\",\"_Location__lat\":123,\"_Lo" +
                "cation__lon\":124},\"_Offer__skills\":[{\"_Skill__id\":1,\"_Skill__name\":\"Python\",\"_Skill__type" +
                "\":\"Programming Language\"}],\"_Offer__languages\":[{\"_Language__id\":1,\"_Language__code\":\"it\"" +
                "}]}]\n";

        Skill skill = new Skill("Python", "Programming Language");
        List<Skill> skills = new ArrayList<>();
        skills.add(skill);

        Language lang = new Language("it");
        languageList = new ArrayList<>();
        languageList.add(lang);

        devCorrect = new Developer(
                "Antonino",
                "Lorenzo",
                "bio",
                "anton@gmail.com",
                "S3cur3Pass_w0rd",
                "",
                skills,
                languageList
        );
    }

    @Test
    @Order(1)
    public void searchOfferQueryError() {
        Assertions.assertThrows(
                InvalidParameterException.class,
                () -> searchOfferEngine.search(nnQuery, devCorrect)
        );
    }

    @Test
    @Order(2)
    public void searchOfferNullDeveloper() {
        Developer devNull = null;
        when(client.sendRequest(anyString()))
                .thenReturn(Optional.of(clientReturn));

        List<Offer> offers = searchOfferEngine.search(query, devNull);
        Assertions.assertNotNull(offers);
    }

    @Test
    @Order(3)
    public void searchOfferNullSkills() {
        List<Skill> skills = null;
        Developer devNullSkills = new Developer(
                "Antonino",
                "Lorenzo",
                "bio",
                "anton@gmail.com",
                "S3cur3Pass_w0rd",
                "",
                skills,
                languageList
        );
        when(client.sendRequest(anyString()))
                .thenReturn(Optional.of(clientReturn));

        List<Offer> offers = searchOfferEngine.search(query, devNullSkills);
        Assertions.assertNotNull(offers);
    }

    @Test
    @Order(4)
    public void searchOfferEmptySkills() {
        List<Skill> skills = new ArrayList<>();
        Developer devEmptySkills = new Developer(
                "Antonino",
                "Lorenzo",
                "bio",
                "anton@gmail.com",
                "S3cur3Pass_w0rd",
                "",
                skills,
                languageList
        );
        when(client.sendRequest(anyString()))
                .thenReturn(Optional.of(clientReturn));

        List<Offer> offers = searchOfferEngine.search(query, devEmptySkills);
        Assertions.assertNotNull(offers);
    }

    @Test
    @Order(5)
    public void searchOfferSuccess() {
        when(client.sendRequest(anyString()))
                .thenReturn(Optional.of(clientReturn));

        List<Offer> offers = searchOfferEngine.search(query, devCorrect);
        Assertions.assertNotNull(offers);
    }
}
