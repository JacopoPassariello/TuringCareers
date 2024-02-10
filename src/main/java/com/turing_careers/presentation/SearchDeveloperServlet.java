package com.turing_careers.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.turing_careers.data.model.Developer;
import com.turing_careers.data.model.Employer;
import com.turing_careers.data.model.Offer;
import com.turing_careers.logic.search.ClientFactory;
import com.turing_careers.logic.search.ClientType;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SearchDeveloperServlet extends HttpServlet {
    /**
     * Fornisce le funzionalità relative alla ricerca di Developer, controllando
     * che l'accesso sia consentito solo ai Datori di Lavoro.
     * */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Employer emp = null;

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

            emp = (Employer) request.getSession().getAttribute("user");

            String query = request.getParameter("query");

            if (!query.isEmpty() && !query.equals(" ")) {

                List<Offer> offers = new ArrayList<>();
                        /*
                            new ClientFactory()
                            .setType(ClientType.DEVELOPER)
                            .getRecommenderEngine()
                            .search(query, emp);
                        */
                ObjectMapper objectMapper = new ObjectMapper();
                String offersJSON = objectMapper.writeValueAsString(offers);

                response.setContentType("application/json");
                response.getWriter().print(offersJSON);
                response.setStatus(200);
            } else {
                response.setStatus(400);
                RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
                dispatcher.forward(request, response);
            }
        } else if (userType.equals("developer")) {
            response.setStatus(400);
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);
        }
    }
}
