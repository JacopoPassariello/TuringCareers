package com.turing_careers.presentation;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.turing_careers.data.dao.PersistenceException;
import com.turing_careers.data.model.*;
import com.turing_careers.logic.auth.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.stream.Collectors;

@WebServlet(name = "AuthenticationServlet", value = "/AuthenticationServlet")
public class AuthenticationServlet extends HttpServlet {

    /**
     * Expose user authentication functionality, based on authType parameter makes login or signup,
     * based on userType makes login or signup of the specified profile type
     * */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String authType = request.getParameter("authType");
        String userType = request.getParameter("userType");

        String mail = request.getParameter("email");
        String password = request.getParameter("password");

        // Create authenticator one time only
        Authenticator authenticator;
        if (userType.equals("developer"))
            authenticator = new DeveloperAuthenticator();
        else if (userType.equals("employer"))
            authenticator = new EmployerAuthenticator();
        else {
            // TODO: handle error
            throw new ServletException("Invalid UserType");
        }

        System.out.println("Logging " + userType);
        try {
            if (authType.equals("login")) {
                // Login
                User u = authenticator.loginUser(mail, password);
                request.getSession().setAttribute("userType", userType);
                request.getSession().setAttribute("user", u);
            } else if (authType.equals("register")) {
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.configure(
                        DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                        false
                );
                String jsonString = request.getReader().lines().collect(Collectors
                        .joining(System.lineSeparator())
                );
                System.out.println(jsonString);
                if (userType.equals("developer")) {
                    System.out.println("Registering developer");
                    Developer dev = objectMapper.readValue(jsonString, Developer.class);
                    System.out.println(dev);
                    dev = (Developer) authenticator.signupUser(dev);
                    System.out.println("Developer Post-Auth: \n" + dev);
                    request.getSession().setAttribute("userType", userType);
                    request.getSession().setAttribute("user", dev);
                } else {
                    Employer emp = objectMapper.readValue(jsonString, Employer.class);
                    emp = (Employer) authenticator.signupUser(emp);
                    request.getSession().setAttribute("userType", userType);
                    request.getSession().setAttribute("user", emp);
                }
            } else {
                // TODO: handle error
                throw new ServletException("Invalid AuthType");
            }
        } catch (InvalidCredentialsException invalidCredentials) {
            throw new ServletException(
                    "Invalid Credentials: " + mail + ": " + password +
                            "\nError: " + invalidCredentials.getMessage()
            );
        } catch (PersistenceException | Exception signupError) {
            throw new ServletException("Signup Error: " + signupError.getMessage());
        }

        System.out.println("success");
        String redirectUrl = "index.jsp";
        String jsonResponse = "{\"redirectUrl\": \"" + redirectUrl + "\"}";
        response.setContentType("application/json");
        response.getWriter().write(jsonResponse);

    }
}