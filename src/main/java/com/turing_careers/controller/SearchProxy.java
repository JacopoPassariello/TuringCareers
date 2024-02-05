package com.turing_careers.controller;

import com.turing_careers.data.model.Developer;
import com.turing_careers.data.model.Offer;
import jakarta.ws.rs.ClientErrorException;
import jakarta.ws.rs.ServerErrorException;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class SearchProxy {
    private Client client;
    private WebTarget target;

    public SearchProxy() {
        this.client = ClientBuilder.newClient();
        this.target = client.target("http://localhost:8000/api");
    }

    /**
     * TODO: Implement
     * */
    public Optional<List<Offer>> searchOffer(String query, Developer user) {
        return Optional.empty();
    }

    /**
     * TODO: Implement
     * */
    public Optional<List<Developer>> searchDeveloper(String query) {
        return Optional.empty();
    }

    /**
     * TODO: Remove
     * */
    public void test() {
        Response response = null;
        try {
            response = target.request(MediaType.APPLICATION_JSON).get();
        } catch (ClientErrorException | ServerErrorException clientError) {
            // Handle exception
        }

        if (response != null) {
            Map<String, Integer> resultMap = response.readEntity(new GenericType<Map<String, Integer>>() { } );
            System.out.println("Response: " + resultMap);
        } else {
            System.out.println("Error");
        }
    }

    public static void main(String[] args) {
        SearchProxy search = new SearchProxy();
        search.test();
    }
}
