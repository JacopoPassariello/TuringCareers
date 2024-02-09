package com.turing_careers.logic.suggestions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.turing_careers.data.model.Location;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author Antonino Lorenzo
 * */
public class LocationClient {
    private static final String API_ENDPOINT = "https://maps.googleapis.com/maps/api/place" +
                                                "/textsearch/json?query=%s&key=%s";
    public static List<LocationMock> getSuggestions(String query) {
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
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                String entity = response.readEntity(String.class);
                System.out.println(entity);
                LocationMock[] locations = objectMapper.readValue(
                        entity,
                        LocationMock[].class
                );
                System.out.println("Done");
                return Arrays.asList(locations);
            } catch (JsonProcessingException ex) {

            }
        } else {
            System.out.println("Error: " + response.getStatus());
        }

        return new ArrayList<>();
    }

    public static void main(String[] args) {
        List<LocationMock> locMoc = LocationClient.getSuggestions("Rom");

        for (LocationMock loc : locMoc) {
            System.out.println(
                loc
            );
        }
    }
}
