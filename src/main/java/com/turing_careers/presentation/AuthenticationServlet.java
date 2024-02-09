package com.turing_careers.presentation;

import com.turing_careers.data.model.Developer;
import com.turing_careers.data.model.Employer;
import com.turing_careers.logic.auth.*;
import com.turing_careers.logic.user.UpdateProfileException;
import com.turing_careers.logic.user.UserManager;
import com.turing_careers.logic.user.UserNotValidException;
import jakarta.persistence.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

@WebServlet(name = "AuthenticationServlet", value = "/AuthenticationServlet")
public class AuthenticationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String authType = request.getParameter("authType");
        String userType = request.getParameter("userType");

        String mail = request.getParameter("mail");
        String password = request.getParameter("password");

        boolean authOutcome = false;
        
        if (authType.equals("login")) {
            if (userType.equals("developer")) {

                DeveloperAuthenticator devAuth = new DeveloperAuthenticator();
                try {
                    Argon2Encryption encryptor = new Argon2Encryption();
                    String encryptedPassword = encryptor.encrypt(password);
                    devAuth.loginUser(mail, encryptedPassword);
                } catch (InvalidCredentialsException e) {
                    throw new RuntimeException(e);
                }
                HttpSession session = request.getSession();
                session.setAttribute("loggedIn", "true");
                //session.setAttribute("user", emp);

            } else if (userType.equals("employer")) {

                EmployerAuthenticator empAuth = new EmployerAuthenticator();
                try {
                    Argon2Encryption encryptor = new Argon2Encryption();
                    String encryptedPassword = encryptor.encrypt(password);
                    empAuth.loginUser(mail, encryptedPassword);
                } catch (InvalidCredentialsException e) {
                    throw new RuntimeException(e);
                }
                HttpSession session = request.getSession();
                session.setAttribute("loggedIn", "true");
                //session.setAttribute("user", emp);
            }
        } else if (authType.equals("register")) {

            final String firstname = request.getParameter("firstname");
            final String lastname = request.getParameter("lastname");
            //controlliamo mail e password passati dall'utente per verificare
            //che rispettino il giusto formato
            if (!this.validate(request)) {
                authOutcome = false;
                proceed(request, response, authType, authOutcome);
            }
            if (userType.equals("developer")) {
                Developer dev = new Developer();
                try {
                    UserManager.createProfile(dev);
                } catch (UpdateProfileException e) {
                    throw new RuntimeException(e);
                } catch (UserNotValidException e) {
                    throw new RuntimeException(e);
                }

                HttpSession session = request.getSession();
                session.setAttribute("isLoggedIn", "true");
                session.setAttribute("utente", dev);
            } else if (userType.equals("employer")) {
                Employer emp = new Employer();
                HttpSession session = request.getSession();
                session.setAttribute("isLoggedIn", "true");
                session.setAttribute("utente", emp);
            }
        }
        proceed(request, response, authType, authOutcome);
    }

    /**
     * TODO:
     * - Implementare Authenticator: classe nel package domain che gestisce logica di login e logout
     * - Implementare DeveloperDAO/Repository e EmployerDAO/Repository: classi che effettuano query al database
     * */

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //vuoto
    }

    private void proceed(HttpServletRequest request, HttpServletResponse response, String authType, Boolean authOutcome) throws ServletException, IOException {
        if (authOutcome) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);
        } else {
            request.setAttribute("authOutcome", "negative");
            if (authType.equals("login")) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("login.html");
                dispatcher.forward(request, response);
            } else if (authType.equals("register")) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("subscription.jsp");
                dispatcher.forward(request, response);
            }
        }
    }

    private boolean validate(HttpServletRequest request) {

        final String firstname = request.getParameter("firstname");
        final String lastname = request.getParameter("lastname");
        final String bio = request.getParameter("bio");
        String mail = request.getParameter("mail");
        String password = request.getParameter("password");
        Pattern mailPattern = Pattern.compile("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$");

        if (firstname.length() == 0
                || firstname.length() > 32
                || lastname.length() == 0
                || lastname.length() > 64
                || bio.length() > 2048
                || mail.length() == 0
                || !mailPattern.matcher(mail).matches()
        ) return false;
        return true;
    }
}