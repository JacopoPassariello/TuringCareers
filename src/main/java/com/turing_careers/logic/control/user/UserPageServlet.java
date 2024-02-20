package com.turing_careers.logic.control.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.turing_careers.data.dao.PersistenceException;
import com.turing_careers.data.model.*;
import com.turing_careers.logic.service.user.UserManager;
import com.turing_careers.logic.service.user.UserValidator;
import com.turing_careers.logic.service.utils.ValidationException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Servlet incaricata di esporre le funzionalità di visualizzazione e modifica di un profilo
 */
@WebServlet(name = "UserPageServlet", value = "/user")
public class UserPageServlet extends HttpServlet {

    /**
     * Ritorna la pagina di un profilo o le informazioni di uno Sviluppatore o un Employer
     * */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") == null) {
            request.getRequestDispatcher("index.html")
                    .forward(request, response);
        }

        String userType = (String) request.getSession().getAttribute("userType");
        String requestType = request.getParameter("requestType");

        if (requestType != null && requestType.equals("info")) {
            // Return User Info
            response.setContentType("application/json");
            ObjectMapper objectMapper = new ObjectMapper();

            User u = (User) request.getSession().getAttribute("user");
            if (userType.equals("employer")) {
                // Return Employer and Employer Offers
                List<Offer> offers = UserManager.getEmployerOffers((Employer) u);
                Map<Employer, List<Offer>> map = new HashMap<>();
                map.put((Employer) u, offers);

                response.getWriter().write(
                        objectMapper
                                .writeValueAsString(map)
                );
            } else if (userType.equals("developer")) {
                // TODO: add developer data
                response.getWriter().write(
                        objectMapper
                                .writeValueAsString(u)
                );
            } else {
                request.getRequestDispatcher("index.html")
                        .forward(request, response);
            }
        } else {
            // Return Page
            response.setContentType("text/html");

            if (userType.equals("developer")) {
                request.getRequestDispatcher("/developer_page.html")
                        .forward(request, response);
            } else if (userType.equals("employer")) {
                request.getRequestDispatcher("/employer_page.html")
                        .forward(request, response);
            }
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
