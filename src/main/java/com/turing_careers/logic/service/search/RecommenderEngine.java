package com.turing_careers.logic.service.search;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.turing_careers.data.dao.OfferDAO;
import com.turing_careers.data.model.Developer;
import com.turing_careers.data.model.Offer;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Created by ClientFactory, serves as interface for ApiClient that manages the requests to Recommendations API.
 * @author Antonino Lorenzo
 * */
public class RecommenderEngine {
    private ClientType type;
    private ApiClient client;
    public RecommenderEngine(ClientType type) {
        this.type = type;
        this.client = new ApiClient();
    }

    public void setClient(ApiClient client) {
        this.client = client;
    }

    /**
     *
     * */
    public List<Developer> search(Offer offer) {
        if (this.type != ClientType.DEVELOPER)
            throw new InvalidParameterException();

        this.client.setRequestBody(offer);
        Optional<String> itemsOpt = client.sendRequest("/engine/v1/developers");
        if (itemsOpt.isPresent()) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                return Arrays.asList(
                    objectMapper
                            .readValue(
                                    itemsOpt.get(),
                                    Developer[].class
                            )
                );
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }

        return new ArrayList<>();
    }

    /**
     * Used to search offers, uses ApiClient to make a request to Recommendations API.
     * @param query: textual query received by client, will be matched against Offer titles and descriptions
     * @param user: the user making the request, it should be a developers
     * */
    public List<Offer> search(String query, Developer user) throws RuntimeException {
        if (this.type != ClientType.OFFER)
            throw new InvalidParameterException("Client should be ClientType.OFFER");

        if (query == null || query.isEmpty())
            throw new InvalidParameterException("Invalid query, empty or none");

        if (user == null || user.getSkills() == null || user.getSkills().isEmpty()) {
            // TODO: log error
            return OfferDAO.getInstance()
                    .getOfferByQuery(query);
        }

        this.client.setRequestBody(query, user);
        String type = null;
        if (query.equals("RECOMMEND"))
            type = "recommend";
        else
            type = "search";

        Optional<String> itemsOpt = client.sendRequest("engine/v1/offers/search");

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
