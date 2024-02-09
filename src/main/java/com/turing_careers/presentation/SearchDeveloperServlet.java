package com.turing_careers.presentation;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class SearchDeveloperServlet extends HttpServlet {
    /**
     * Fornisce le funzionalità relative alla ricerca di Developer, controllando
     * che l'accesso sia consentito solo ai Datori di Lavoro.
     * */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
