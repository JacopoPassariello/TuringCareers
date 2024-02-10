package com.turing_careers.presentation;

import com.turing_careers.data.dao.PersistenceException;
import com.turing_careers.data.model.*;
import com.turing_careers.logic.offer.OfferManager;
import com.turing_careers.logic.user.UserManager;
import com.turing_careers.logic.validator.UserValidator;
import com.turing_careers.logic.validator.ValidationException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class UserPageServlet extends HttpServlet {

    /**
     * Ritorna la pagina di un profilo di uno Sviluppatore o un Employer
     * */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userType = (String) request.getSession().getAttribute("userType");
        Developer dev = null;
        Employer emp = null;

        if (request.getSession().getAttribute("user") == null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);
        }

        if (userType.equals("developer"))
            dev = (Developer) request.getSession().getAttribute("user");
        else if (userType.equals("employer"))
            emp = (Employer) request.getSession().getAttribute("user");


        if (userType.equals("developer")) {
            List<Offer> offers = dev.getSavedOffers();
            List<Language> languages = dev.getLanguages();
            Location  location = dev.getLocation();

            if (!offers.isEmpty()) {
                request.setAttribute("noOffers", false);
                request.setAttribute("offers", offers);
            } else {
                request.setAttribute("noOffers", true);
            }

            if (!languages.isEmpty()) {
                request.setAttribute("noLanguages", false);
                request.setAttribute("languages", languages);
            } else {
                request.setAttribute("noLanguages", true);
            }

            if (location == null) {
                request.setAttribute("noLocation", false);
                request.setAttribute("location", location);
            } else {
                request.setAttribute("noLocation", true);
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher("userPage.jsp");
        } else if (userType.equals("employer")) {
            List<Offer> offers = emp.getOffers();
            List<Developer> developers = emp.getSavedDevelopers();

            if (!offers.isEmpty()) {
                request.setAttribute("noOffers", false);
                request.setAttribute("offers", offers);
            } else {
                request.setAttribute("noOffers", true);
            }
            

            if (!developers.isEmpty()) {
                request.setAttribute("noDevelopers", false);
                request.setAttribute("developers", developers);
            } else {
                request.setAttribute("noDevelopers", true);
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher("userPage.jsp");
        }
    }

    /**
     * Gestisce i cambiamenti del profilo di uno Sviluppatore o di un Employer
     * */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        // TODO: response set type: text-plain (success | failed)

        // Verify parameters
        String userType = request.getParameter("userType");
        if (userType == null || userType.trim().isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        } else if (request.getParameter("newFirstName") == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        } else if (request.getParameter("newLastName") == null ) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        } else if (request.getParameter("newMail") == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        } else if (request.getParameter("newPassword") == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        // Try update
        String newFirstName =  request.getParameter("newFirstName");
        String newLastName = request.getParameter("newLastName");
        String newMail = request.getParameter("newEmail");
        String newPassword = request.getParameter("newPassword");
        try {
            if (userType.equals("developer")) {
                Developer dev =  (Developer) request.getSession().getAttribute("user");
                dev.setFirstName(newFirstName);
                dev.setLastName(newLastName);
                dev.setMail(newMail);
                dev.setPassword(newPassword);

                UserValidator.checkValidity(dev);
                UserManager.editProfile(dev);
                request.setAttribute("user", dev);

            } else if (userType.equals("employer")) {
                Employer emp =  (Employer) request.getSession().getAttribute("user");
                emp.setFirstName(newFirstName);
                emp.setLastName(newLastName);
                emp.setMail(newMail);
                emp.setPassword(newPassword);

                UserValidator.checkValidity(emp);
                UserManager.editProfile(emp);
                request.setAttribute("user", emp);
            }
        } catch (ValidationException e) {
            System.out.println("Parametri utente non validi.");
            // TODO: send failed
        } catch (PersistenceException e) {
            System.out.println("Errore nell'user manager per la persistenza.");
            // TODO: send failed
        }

        // TODO: send success
    }
}
