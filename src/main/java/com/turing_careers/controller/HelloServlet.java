package com.turing_careers.controller;

import java.io.*;

import com.turing_careers.domain.SearchProxy;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/test-api")
public class HelloServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");

        SearchProxy search = new SearchProxy();
        search.test();
    }
}
