package com.turing_careers.logic.control.offer;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.turing_careers.data.dao.PersistenceException;
import com.turing_careers.data.model.Employer;
import com.turing_careers.data.model.Offer;
import com.turing_careers.data.model.Skill;
import com.turing_careers.data.model.User;
import com.turing_careers.logic.service.offer.OfferManager;
import com.turing_careers.logic.service.utils.ValidationException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.stream.Collectors;

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO: check user is employer
        // TODO: manage offer modify
        String offerJSON = request.getReader().lines().collect(Collectors
                .joining(System.lineSeparator())
        );
        ObjectMapper objectMapper = new ObjectMapper();
        Offer offer = objectMapper.readValue(offerJSON, Offer.class);
        Employer emp = (Employer) request.getSession().getAttribute("user");
        offer.setEmployer(emp);

        try {
            OfferManager.createOffer(offer);
        } catch (ValidationException e) {
            System.out.println("[OfferServlet POST] Invalid Offer: " + e.getMessage());
            throw new RuntimeException(e);
        } catch (PersistenceException e) {
            System.out.println("[OfferServlet POST] Persistence Exception: " + e.getMessage());
            throw new RuntimeException(e);
        }


        response.setContentType("application/json");
        response.getWriter().write(
                objectMapper
                        .writeValueAsString(offer)
        );
    }
}
