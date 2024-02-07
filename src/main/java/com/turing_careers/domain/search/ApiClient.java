package com.turing_careers.domain.search;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.turing_careers.data.model.*;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ApiClient {
    private static final String API_ENDPOINT  = "http://localhost:8000/";
    private RequestBody requestBody;
    private Client requestClient;

    public ApiClient(Item item) {
        this.requestClient = ClientBuilder.newClient();
        this.requestBody = new RequestBody(item);
    }

    public ApiClient(String query) {
        this.requestClient = ClientBuilder.newClient();
        this.requestBody = new RequestBody(query);
    }

    public ApiClient(String query, User user) {
        this.requestClient = ClientBuilder.newClient();
        this.requestBody = new RequestBody(query, user);
    }

    public Optional<List<Item>> sendRequest(String endpoint) {
        // Build Target Path
        WebTarget target = this.requestClient
                .target(ApiClient.API_ENDPOINT)
                .path(endpoint);

        // Get JSON Entity
        Entity<String> entity = Entity.entity(
                this.requestBody.getJsonRequest(),
                MediaType.APPLICATION_JSON
        );

        Response response = target
                .request(MediaType.APPLICATION_JSON)
                .post(entity);

        if (response.getStatus() == Response.Status.OK.getStatusCode()) {
            //GenericType<List<Item>> genericType = new GenericType<>(){};
            //List<Item> items = response.readEntity(genericType);
            //return Optional.ofNullable(items);
            System.out.println(response.readEntity(String.class));
            return Optional.empty();
        } else {
            System.out.println("Error: " + response.getStatus());
            return Optional.empty();
        }
    }

    public static void main(String[] args) {
        Skill skill = new Skill("Python", "Programming Language");
        List<Skill> skills = new ArrayList<>();
        skills.add(skill);

        Language lang = new Language("it");
        List<Language> languages = new ArrayList<>();
        languages.add(lang);

        Developer dev = new Developer(
                1L,
                "Antonino",
                "Lorenzo",
                "bio",
                "anton@gmail.com",
                "1234",
                new Location(),
                skills,
                languages
        );

        ApiClient client = new ApiClient("Web developer", dev);
        Optional<List<Item>> itemsOpt = client.sendRequest("engine/v1/offers");
        /*
        if (itemsOpt.isPresent()) {
            List<Item> items = itemsOpt.get();
            List<Offer> offers = new ArrayList<>();

            for (Item item : items) {
                Offer offer = new Offer();
                offer.setId(((Offer) item).getId());
                offer.setTitle(((Offer) item).getTitle());
                offer.setDescription(((Offer) item).getDescription());
                offer.setState(((Offer) item).getState());
                offer.setLocationType(((Offer) item).getLocationType());
                offers.add(offer);
            }

            for (Offer o : offers) {
                System.out.println(o);
            }
        }*/
    }
}
