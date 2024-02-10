package com.turing_careers.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.turing_careers.data.dao.PersistenceException;
import com.turing_careers.data.model.Offer;
import com.turing_careers.logic.offer.OfferManager;
import com.turing_careers.logic.validator.ValidationException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class OfferServlet extends HttpServlet {

    /**
     * Ritorna la pagina dell'offerta
     * */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long offerId = Long.parseLong(req.getParameter("offer_id"));
        Offer offer = OfferManager.getOffer(offerId);
        req.setAttribute("offer", offer);

        RequestDispatcher dispatcher = req.getRequestDispatcher("offer_page.jsp");
        dispatcher.forward(req, resp);
    }

    /**
     * Permette al datore di lavoro di creare/modificare l'offerta
     * */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String offerJSON = req.getParameter("offer");
        ObjectMapper objectMapper = new ObjectMapper();

        Offer offer = objectMapper.readValue(offerJSON, Offer.class);
        try {
            OfferManager.createOffer(offer);
        } catch (PersistenceException e) {
            throw new RuntimeException(e);
        } catch (ValidationException e) {
            throw new RuntimeException(e);
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("employer_page.jsp");
    }
}
