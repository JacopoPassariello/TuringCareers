package tcs;

import com.turing_careers.data.model.Employer;
import com.turing_careers.logic.auth.Authenticator;
import com.turing_careers.logic.auth.EmployerAuthenticator;
import com.turing_careers.logic.utils.ValidationException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

/**
 * Implementation of TCS_GU_2, see Test Case Specification.
 * */
@TestMethodOrder(OrderAnnotation.class)
public class EmployerSignupTest {
    private static Authenticator authenticator;

    @BeforeAll
    public static void setup() {
        authenticator = new EmployerAuthenticator();
    }

    @Test
    @Order(1)
    public void nameLengthError() {
        Employer employer = new Employer(
                "",
                "Sardo",
                "e.mail@vali.da",
                "Pass_V4lida",
                "IBM"
        );

        Assertions.assertThrows(
                ValidationException.class,
                () -> authenticator.signupUser(employer)
        );
    }

    @Test
    @Order(2)
    public void surnameLengthError() {
        Employer employer = new Employer(
                "Stefano",
                "",
                "e.mail@vali.da",
                "Pass_V4lida",
                "IBM"
        );
        Assertions.assertThrows(
                ValidationException.class,
                () -> authenticator.signupUser(employer)
        );
    }

    @Test
    @Order(3)
    public void companyLengthError() {
        Employer employer = new Employer(
                "Stefano",
                "Sardo",
                "e.mail@vali.da",
                "Pass_V4lida",
                ""
        );
        Assertions.assertThrows(
                ValidationException.class,
                () -> authenticator.signupUser(employer)
        );
    }
    @Test
    @Order(4)
    public void mailLengthError() {
        Employer employer = new Employer(
                "Stefano",
                "Sardo",
                "",
                "Pass_V4lida",
                "IBM"
        );
        Assertions.assertThrows(
                ValidationException.class,
                () -> authenticator.signupUser(employer)
        );
    }

    @Test
    @Order(5)
    public void mailFormatError() {
        Employer employer = new Employer(
                "Stefano",
                "Sardo",
                "e.mail.non.valida",
                "Pass_V4lida",
                "IBM"
        );
        Assertions.assertThrows(
                ValidationException.class,
                () -> authenticator.signupUser(employer)
        );
    }

    @Test
    @Order(6)
    public void passwordLengthError() {
        Employer employer = new Employer(
                "Stefano",
                "Sardo",
                "e.mail@vali.da",
                "123",
                "IBM"
        );
        Assertions.assertThrows(
                ValidationException.class,
                () -> authenticator.signupUser(employer)
        );
    }

    @Test
    @Order(7)
    public void passwordFormatError() {
        Employer employer = new Employer(
                "Stefano",
                "Sardo",
                "e.mail@vali.da",
                "passnonv@lid@",
                "IBM"
        );
        Assertions.assertThrows(
                ValidationException.class,
                () -> authenticator.signupUser(employer)
        );
    }

    @Test
    @Order(8)
    public void success() {
        Employer employer = new Employer(
                "Stefano",
                "Sardo",
                "e.mail@vali.da",
                "Pass_V4lida",
                "IBM"
        );
        Assertions.assertDoesNotThrow(
                () -> authenticator.signupUser(employer),
                "Signup Failed"
        );
    }
}
