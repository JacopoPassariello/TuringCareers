package com.turing_careers.domain.search;

import com.turing_careers.data.model.Item;
import com.turing_careers.data.model.User;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.WebTarget;

import java.util.List;
import java.util.Optional;

public class ApiClient {
    private static final String API_ENDPOINT  = "http://localhost:8000/";
    private RequestBody requestBody;
    private Client requestClient;

    public ApiClient(Item item) {

    }

    public ApiClient(String query) {

    }

    public ApiClient(String query, User user) {

    }

    public Optional<List<Item>> sendRequest(String endpoint) {
        return Optional.empty();
    }
}
