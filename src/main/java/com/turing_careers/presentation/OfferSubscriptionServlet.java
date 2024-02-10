package com.turing_careers.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.turing_careers.data.dao.PersistenceException;
import com.turing_careers.data.model.Developer;
import com.turing_careers.data.model.Employer;
import com.turing_careers.data.model.Offer;
import com.turing_careers.logic.offer.OfferManager;
import com.turing_careers.logic.validator.ValidationException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "OfferSubscriptionServlet", value = "/OfferSubscriptions")
public class OfferSubscriptionServlet extends HttpServlet {

    /**
     * Mostra ai datori di lavoro gli sviluppatori interessati ad una offerta e permette agli sviluppatori di salvare una offerta
     * */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userType = (String) req.getSession().getAttribute("userType");
        Long offerId = Long.parseLong(req.getParameter("offerId"));
        Offer offer = OfferManager.getOffer(offerId);

        if(userType.equals("developer")) {
            Developer dev = (Developer) req.getSession().getAttribute("user");
            try {
                OfferManager.subscribeToOffer(dev, offer);
            } catch (PersistenceException e) {
                resp.setStatus(500);
                resp.setContentType("application/json");
                resp.getWriter().print("{\"error\":\"PersistenceException\"}");
            } catch (ValidationException e) {
                resp.setStatus(400);
                resp.setContentType("application/json");
                resp.getWriter().print("{\"error\":\"ValidationException\"}");
            }
            resp.setStatus(200);
        }
        else if(userType.equals("employer")) {
            List<Developer> subscribedDevelopers = offer.getSubscribedDevelopers();

            ObjectMapper objectMapper = new ObjectMapper();
            String subscribedDevelopersJSON = objectMapper.writeValueAsString(subscribedDevelopers);

            resp.setContentType("application/json");
            resp.getWriter().print(subscribedDevelopersJSON);
            resp.setStatus(200);
        }
        else {
            resp.setStatus(400);
            resp.setContentType("application/json");
            resp.getWriter().print("{\"error\":\"UserNotLoggedIn\"}");
        }
    }
}
