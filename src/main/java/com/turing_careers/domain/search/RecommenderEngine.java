package com.turing_careers.domain.search;

import com.google.protobuf.Api;
import com.turing_careers.data.model.Developer;
import com.turing_careers.data.model.Item;
import com.turing_careers.data.model.Offer;
import com.turing_careers.data.model.User;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RecommenderEngine {
    private ClientType type;
    public RecommenderEngine(ClientType type) {
        this.type = type;
    }

    public List<Developer> search(String query) {
        if (this.type != ClientType.DEVELOPER)
            throw new InvalidParameterException();
        // ...
        return new ArrayList<>();
    }

    public List<Developer> match(List<Offer> offers) {
        if (this.type != ClientType.DEVELOPER)
            throw new InvalidParameterException();

        return new ArrayList<>();
    }

    /**
     * Used to search offers
     * */
    public List<Offer> search(String query, Developer user) {
        if (this.type != ClientType.OFFER)
            throw new InvalidParameterException();

        ApiClient client = new ApiClient(query, user);
        client.sendRequest("engine/v1/offers");
        // ...

        return new ArrayList<>();
    }
}
