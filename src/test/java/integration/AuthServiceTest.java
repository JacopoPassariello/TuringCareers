package integration;


import com.turing_careers.data.model.Developer;
import com.turing_careers.data.model.Employer;
import com.turing_careers.domain.auth.DeveloperAuthService;
import com.turing_careers.domain.auth.EmployerAuthService;
import com.turing_careers.domain.auth.InvalidCredentialsException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class AuthServiceTest {

    @Test
    public void testSignupUser() {
        EmployerAuthService authService = new EmployerAuthService();
        Employer employer = new Employer(
                "Tony",
                "Loaf",
                "antoniopagnotta__figo@gmail.com",
                "123456",
                "Pagnotta Inc."
        );

        try {
            authService.signupUser(employer);
        } catch (Exception exception) {
            exception.printStackTrace();
            fail("Unexpected exception occurred: " + exception.getMessage());
        }
    }

    @Test
    public void testLoginUserFail() {
        EmployerAuthService authService = new EmployerAuthService();

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
        EmployerAuthService authService = new EmployerAuthService();

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
