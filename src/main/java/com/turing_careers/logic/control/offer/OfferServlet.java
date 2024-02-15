package com.turing_careers.logic.control.offer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.turing_careers.data.model.Offer;
import com.turing_careers.logic.service.offer.OfferManager;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Servlet incaricata di esporre le funzionalità di interazione utente-offerta
 */
@WebServlet(name = "OfferServlet", value = "/offers")
public class OfferServlet extends HttpServlet {

    /**
     * Ritorna la pagina dell'offerta richiesta dal parametro offer_id
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long offerId = Long.parseLong(req.getParameter("offer_id"));
        Offer offer = OfferManager.getOffer(offerId);
        req.setAttribute("offer", offer);

        RequestDispatcher dispatcher = req.getRequestDispatcher("offer_page.jsp");
        dispatcher.forward(req, resp);
    }

    /**
     * Permette al datore di lavoro di creare/modificare un'offerta
     * */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String offerJSON = req.getParameter("offer");
        ObjectMapper objectMapper = new ObjectMapper();

        Offer offer = objectMapper.readValue(offerJSON, Offer.class);
        System.out.println(offer);

        // TODO: get Employer from session and add to offer, then persist
        //  in this way offer creation do not reload the page.
        /*
        try {
            OfferManager.createOffer(offer);
        } catch (PersistenceException | ValidationException e) {
            throw new RuntimeException(e);
        }
        */

        // TODO: if nothing went wrong send success message, the client will
        //  update the page with the created offer.
        // RequestDispatcher dispatcher = req.getRequestDispatcher("employer_page.jsp");
    }
}
