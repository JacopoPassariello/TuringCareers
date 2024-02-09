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
        String userType = request.getParameter("userType");
        Developer dev = null;
        Employer emp = null;

        if(request.getSession().getAttribute("user") == null){
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);
        }else{
            if (userType.equals("developer"))
                dev = (Developer) request.getSession().getAttribute("user");
            else if (userType.equals("employer"))
                emp = (Employer) request.getSession().getAttribute("user");
        }


        if (userType.equals("developer")) {
            List<Offer> offers = dev.getSavedOffers();
            List<Language> languages = dev.getLanguages();
            Location  location = dev.getLocation();

            if (!offers.isEmpty()) {
                request.setAttribute("noOffers", false);
                request.setAttribute("offers", offers);
            }else{
                request.setAttribute("noOffers", true);
            }

            if (!languages.isEmpty()) {
                request.setAttribute("noLanguages", false);
                request.setAttribute("languages", languages);
            }else{
                request.setAttribute("noLanguages", true);
            }

            if (location == null) {
                request.setAttribute("noLocation", false);
                request.setAttribute("location", location);
            }else{
                request.setAttribute("noLocation", true);
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher("userPage.jsp");
        } else if (userType.equals("employer")) {
            List<Offer> offers = emp.getOffers();
            List<Developer> developers = emp.getSavedDevelopers();

            if (!offers.isEmpty()) {
                request.setAttribute("noOffers", false);
                request.setAttribute("offers", offers);
            }else{
                request.setAttribute("noOffers", true);
            }

            if (!developers.isEmpty()) {
                request.setAttribute("noDevelopers", false);
                request.setAttribute("developers", developers);
            }else{
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
        String newFirstName = "";
        String newLastName = "";
        String newMail = "";
        String newPassword = "";

        String userType = request.getParameter("userType");

        Boolean outcome = true;

        if (request.getParameter("newFirstName") != null  || request.getParameter("newFirstName").equals("") || request.getParameter("newFirstName").equals(" ")) {
            newFirstName =  request.getParameter("newFirstName");
            outcome = false;
        }
        if (request.getParameter("newLastName") != null  || request.getParameter("newLastName").equals("") || request.getParameter("newLastName").equals(" ")) {
            newLastName = request.getParameter("newLastName");
            outcome = false;
        }
        if (request.getParameter("newMail") != null  || request.getParameter("newMail").equals("") || request.getParameter("newMail").equals(" ")) {
            newMail = request.getParameter("newEmail");
            outcome = false;
        }
        if (request.getParameter("newPassword") != null  || request.getParameter("newPassword").equals("") || request.getParameter("newPassword").equals(" ")) {
            newPassword = request.getParameter("newPassword");
            outcome = false;
        }

        if(outcome) {
            if(userType.equals("developer")){

                Developer dev =  (Developer) request.getSession().getAttribute("user");

                try {
                    dev.setFirstName(newFirstName);
                    dev.setLastName(newLastName);
                    dev.setMail(newMail);
                    dev.setPassword(newPassword);
                    UserValidator.checkValidity(dev);
                    UserManager.editProfile(dev);
                } catch (ValidationException e) {
                    System.out.println("Parametri utente non validi.");
                } catch (PersistenceException e) {
                    System.out.println("Errore nell'user manager per la persistenza.");
                }
            }else if(userType.equals("employer")){
                Employer emp =  (Employer) request.getSession().getAttribute("user");
                try {
                    emp.setFirstName(newFirstName);
                    emp.setLastName(newLastName);
                    emp.setMail(newMail);
                    emp.setPassword(newPassword);
                    UserValidator.checkValidity(emp);
                    UserManager.editProfile(emp);
                } catch (ValidationException e) {
                    System.out.println("Parametri utente non validi.");
                } catch (PersistenceException e) {
                    System.out.println("Errore nell'user manager per la persistenza.");
                }
            }
        }
    }
}
