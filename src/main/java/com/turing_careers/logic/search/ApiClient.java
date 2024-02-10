package com.turing_careers.logic.search;

import com.turing_careers.data.model.*;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Optional;

/**
 * The Client for Recommendations API, builds the JSON Body to send in the request with RequestBody class.
 * @author Antonino Lorenzo
 * */
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

    /**
     * @param endpoint: the specific endpoint should be specified by Engine calling sendRequest
     * @return : returns a String representation of the JSON response that is converted by Engines
     * */
    public Optional<String> sendRequest(String endpoint) {
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
            return Optional.of(response.readEntity(String.class));
        } else {
            System.out.println("Error: " + response.getStatus());
            return Optional.empty();
        }
    }
}
