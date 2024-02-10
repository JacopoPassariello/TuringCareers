package com.turing_careers.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.turing_careers.data.model.Location;
import com.turing_careers.logic.suggestions.LocationClient;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Antonino Lorenzo
 * */
@WebServlet(name = "locationsSuggestions", value = "/suggest-locations")
public class LocationSuggestionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");
        resp.setCharacterEncoding("UTF-8");

        String query = req.getParameter("locationQuery");
        if (query == null || query.trim().isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        List<Location> locations;
        try {
            locations = LocationClient.getSuggestions(query);
            for (Location loc : locations) {
                System.out.println(loc);
            }
            if (locations.isEmpty()) {
                resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
                return;
            }
        } catch (Exception ex) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error retrieving location suggestions.");
            return;
        }

        ObjectMapper mapper = new ObjectMapper();
        resp.getWriter().write(
                mapper
                        .writeValueAsString(locations)
        );
    }
}
