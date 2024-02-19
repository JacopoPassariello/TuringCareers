package com.turing_careers.logic.control.search;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.turing_careers.data.model.Developer;
import com.turing_careers.data.model.Employer;
import com.turing_careers.data.model.Offer;
import com.turing_careers.logic.service.search.ClientFactory;
import com.turing_careers.logic.service.search.ClientType;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Servlet incaricata di esporre le funzionalità di ricerca di sviluppatori
 */
@WebServlet(name = "recommendDevelopers", value = "/recommend-developers")
public class SearchDeveloperServlet extends HttpServlet {
    /**
     * Fornisce le funzionalità relative alla ricerca di Developer, controllando che l'accesso sia consentito solo ai Datori di Lavoro.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("userType") == null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);
        }

        String userType = (String) request.getSession().getAttribute("userType");

        if (userType.equals("employer")) {
            if (request.getSession().getAttribute("user") == null) {
                response.setStatus(500);
                RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
                dispatcher.forward(request, response);
            }

            // Get Offer for Recommendation
            ObjectMapper objectMapper = new ObjectMapper();
            String offerJSON = request.getReader().lines().collect(Collectors
                    .joining(System.lineSeparator())
            );
            Offer offer = objectMapper.readValue(offerJSON, Offer.class);
            Employer emp = (Employer) request.getSession().getAttribute("user");
            offer.setEmployer(emp);
            offer.setId(1000L);


            // Getting recommended developers
            List<Developer> developers = new ClientFactory()
                    .setType(ClientType.DEVELOPER)
                    .getRecommenderEngine()
                    .search(offer);

            // Response
            String devsJSON = objectMapper.writeValueAsString(developers);
            response.setContentType("application/json");
            response.getWriter().write(devsJSON);
            response.setStatus(200);

        } else {
            response.setStatus(400);
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);
        }
    }
}
