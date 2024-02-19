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
import java.util.List;

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
        String requestType = request.getParameter("requestType");
        if (requestType != null && requestType.equals("info")) {
            response.setContentType("application/json");

            User u = (User) request.getSession().getAttribute("user");
            // TODO: add employer offers

            ObjectMapper objectMapper = new ObjectMapper();
            response.getWriter().write(
                    objectMapper
                            .writeValueAsString(u)
            );
        } else {
            // Return Page
            response.setContentType("text/html");

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
                request.getRequestDispatcher("/developer_page.html").forward(request, response);
            } else if (userType.equals("employer")) {
                request.getRequestDispatcher("/employer_page.html").forward(request, response);
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
