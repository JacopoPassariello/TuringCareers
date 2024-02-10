package com.turing_careers.presentation;

import com.turing_careers.data.dao.PersistenceException;
import com.turing_careers.data.model.Developer;
import com.turing_careers.data.model.Employer;
import com.turing_careers.logic.auth.*;
import com.turing_careers.logic.user.UserManager;
import com.turing_careers.logic.validator.ValidationException;
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

        String mail = request.getParameter("email");
        String password = request.getParameter("password");

        boolean authOutcome = true;

        if (authType.equals("login")) {
            if (userType.equals("developer")) {

                DeveloperAuthenticator devAuth = new DeveloperAuthenticator();
                try {
                    devAuth.loginUser(mail, password);
                } catch (InvalidCredentialsException e) {
                    authOutcome = false;
                    proceed(request, response, authType, authOutcome);
                }
                HttpSession session = request.getSession();
                session.setAttribute("loggedIn", "true");
                session.setAttribute("user",UserManager.getDeveloperByMail(mail));

            } else if (userType.equals("employer")) {
                EmployerAuthenticator empAuth = new EmployerAuthenticator();
                try {
                    empAuth.loginUser(mail, password);
                    System.out.println("Success: " + authOutcome);
                } catch (InvalidCredentialsException e) {
                    authOutcome = false;
                    proceed(request, response, authType, authOutcome);
                }
                HttpSession session = request.getSession();
                session.setAttribute("loggedIn", "true");
                session.setAttribute("user", UserManager.getEmployerByMail(mail));
            }
        } else if (authType.equals("register")) {

            final String firstname = request.getParameter("firstname");
            final String lastname = request.getParameter("lastname");
            final String bio = request.getParameter("bio");
            //controlliamo mail e password passati dall'utente per verificare
            //che rispettino il formato giusto
            //Per employer: va aggiunto company name.
            //Per developer: vanno aggiunte skill e lingue.
            if (!this.validate(request)) {
                authOutcome = false;
                proceed(request, response, authType, authOutcome);
            }
            if (userType.equals("developer")) {
                Developer dev = new Developer();
                try {
                    UserManager.createProfile(dev); //fallisce sempre
                } catch (PersistenceException e) {
                    //redirect pagina d'errore.
                    authOutcome = false;
                    proceed(request, response, authType, authOutcome);
                    throw new RuntimeException(e);
                } catch (ValidationException e) {
                    //redirect pagina d'errore.
                    authOutcome = false;
                    proceed(request, response, authType, authOutcome);
                    throw new RuntimeException(e);
                }

                HttpSession session = request.getSession();
                session.setAttribute("isLoggedIn", "true");
                session.setAttribute("user", dev);
            } else if (userType.equals("employer")) {
                Employer emp = new Employer();
                try {
                    UserManager.createProfile(emp); //fallisce sempre
                } catch (PersistenceException e) {
                    //redirect a pagina d'errore.
                    authOutcome = false;
                    proceed(request, response, authType, authOutcome);
                    throw new RuntimeException(e);
                } catch (ValidationException e) {
                    //redirect a pagina d'errore
                    authOutcome = false;
                    proceed(request, response, authType, authOutcome);
                    throw new RuntimeException(e);
                }
                HttpSession session = request.getSession();
                session.setAttribute("isLoggedIn", "true");
                session.setAttribute("user", emp);
            }
        }
        proceed(request, response, authType, authOutcome);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //vuoto
    }

    private void proceed(HttpServletRequest request, HttpServletResponse response,
                         String authType, Boolean authOutcome) throws ServletException, IOException {
        if (authOutcome) {
            /*TODO cambiare
             */
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


    //questo lo facciamo già in un package di validazione
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