package com.turing_careers.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.turing_careers.data.model.Skill;
import com.turing_careers.logic.utils.SkillMatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "skillSuggestionServlet", value = "/suggest-skills")
public class SkillSuggestionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");
        resp.setCharacterEncoding("UTF-8");

        String query = req.getParameter("skillsQuery");
        if (query == null || query.trim().isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        List<Skill> skills = SkillMatcher.getSuggestions(query);
        if (skills.isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
            return;
        }

        ObjectMapper mapper = new ObjectMapper();
        resp.getWriter().write(
                mapper
                        .writeValueAsString(skills)
        );
    }
}
