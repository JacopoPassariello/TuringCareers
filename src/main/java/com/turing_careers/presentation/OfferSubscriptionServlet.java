package com.turing_careers.presentation;

import com.turing_careers.data.model.Employer;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class OfferSubscriptionServlet extends HttpServlet {

    /**
     * Mostra ai datori di lavoro gli sviluppatori interessati ad una offerta
     * */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Employer employer = (Employer) req.getSession().getAttribute("user");


    }

    /**
     * Permette agli sviluppatori di salvare una offerta
     * */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
