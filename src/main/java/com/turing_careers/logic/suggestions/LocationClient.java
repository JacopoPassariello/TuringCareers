package com.turing_careers.logic.suggestions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.turing_careers.data.model.Location;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Antonino Lorenzo
 * */
public class LocationClient {
    private static final String API_ENDPOINT = "https://maps.googleapis.com/maps/api/place" +
                                                "/textsearch/json?query=%s&key=%s";

    /**
     * @param query: query for Google Places API
     * */
    public static List<Location> getSuggestions(String query) throws Exception {
        // Setup
        String endpoint = null;
        try {
             endpoint = String.format(
                    API_ENDPOINT,
                    query,
                    System.getenv("PLACES_KEY")
            );
        } catch (NullPointerException noKeyException) {
            System.out.println("Can't Find PLACES_KEY in environment variables");
        } catch (SecurityException ex) {
            System.out.println("Something went wrong: " + ex.getMessage());
        }

        WebTarget target = ClientBuilder
                .newClient()
                .target(endpoint);

        Response response = target
                .request(MediaType.APPLICATION_JSON)
                .get();

        if (response.getStatus() == Response.Status.OK.getStatusCode()) {
            // Parse result
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.configure(
                        DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                        false
                );

                return objectMapper.readValue(
                        response.readEntity(String.class),
                        PlacesResponse.class
                ).getLocations();
            } catch (JsonProcessingException ex) {
                throw new Exception(ex.getMessage());
            }
        } else {
            throw new Exception("Response Status: " + response.getStatus());
        }
    }

    public static void main(String[] args) throws Exception {
        List<Location> locations = LocationClient.getSuggestions("Rom");
        for (Location loc : locations) {
            System.out.println("> " + loc);
        }
    }
}
