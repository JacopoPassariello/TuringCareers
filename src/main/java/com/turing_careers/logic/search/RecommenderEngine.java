package com.turing_careers.logic.search;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.turing_careers.data.model.Developer;
import com.turing_careers.data.model.Offer;
import com.turing_careers.data.model.Skill;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Created by ClientFactory, serves as interface for ApiClient that manages the requests to Recommendations API.
 * */
public class RecommenderEngine {
    private ClientType type;
    public RecommenderEngine(ClientType type) {
        this.type = type;
    }

    /**
     *
     * */
    public List<Developer> search(Offer offer) {
        if (this.type != ClientType.DEVELOPER)
            throw new InvalidParameterException();

        return this.search(offer.getSkills());
    }

    /**
     *
     * */
    public List<Developer> search(List<Skill> skills) {
        if (this.type != ClientType.DEVELOPER)
            throw new InvalidParameterException();

        return new ArrayList<>();
    }

    /**
     * Used to search offers, uses ApiClient to make a request to Recommendations API.
     * @param query: textual query received by client, will be matched against Offer titles and descriptions
     * @param user: the user making the request, it should be a developers
     * */
    public List<Offer> search(String query, Developer user) throws RuntimeException {
        if (this.type != ClientType.OFFER)
            throw new InvalidParameterException();

        ApiClient client = new ApiClient(query, user);
        Optional<String> itemsOpt = client.sendRequest("engine/v1/offers");

        List<Offer> offerList = new ArrayList<>();
        if (itemsOpt.isPresent()) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                return Arrays.asList(
                        objectMapper
                                .readValue(
                                        itemsOpt.get(),
                                        Offer[].class
                                )
                );
            } catch (JsonProcessingException error) {
                throw new RuntimeException(error.toString());
            }
        }
        return offerList;
    }
}
