package integration;


import com.turing_careers.data.dao.PersistenceException;
import com.turing_careers.data.model.Employer;
import com.turing_careers.logic.auth.EmployerAuthenticator;
import com.turing_careers.logic.auth.InvalidCredentialsException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class AuthServiceTest {

    @Test
    public void testSignupUser() {
        EmployerAuthenticator authService = new EmployerAuthenticator();
        Employer employer = new Employer(
                "Tony",
                "Loaf",
                "antoniopagnotta__figo@gmail.com",
                "123456",
                "Pagnotta Inc."
        );

        try {
            authService.signupUser(employer);
        } catch (PersistenceException exception) {
            exception.printStackTrace();
            fail("Unexpected exception occurred: " + exception.getMessage());
        }
    }

    @Test
    public void testLoginUserFail() {
        EmployerAuthenticator authService = new EmployerAuthenticator();

        try {
            authService.loginUser(
                    "antoniopagnotta__figo@gmail.com",
                    "123"
            );
        } catch (InvalidCredentialsException ex) {
            Assertions.assertTrue(true);
        }
    }

    @Test
    public void testLoginUserSuccess() {
        EmployerAuthenticator authService = new EmployerAuthenticator();

        try {
            authService.loginUser(
                    "antoniopagnotta__figo@gmail.com",
                    "123456"
            );
        } catch (InvalidCredentialsException ex) {
            ex.printStackTrace();
            fail("Unexpected exception occurred: " + ex.getMessage());
        }
    }
}
