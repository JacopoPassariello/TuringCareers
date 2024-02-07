package com.turing_careers.domain.search;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    public List<Offer> search(String query, Developer user) throws RuntimeException {
        if (this.type != ClientType.OFFER)
            throw new InvalidParameterException();

        ApiClient client = new ApiClient(query, user);
        Optional<String> itemsOpt = client.sendRequest("engine/v1/offers");

        if (itemsOpt.isPresent()) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                OfferMock[] offers = objectMapper.readValue(itemsOpt.get(), OfferMock[].class);
                List<Offer> offerList = new ArrayList<>();

                for (OfferMock mock : offers) {
                    offerList.add(
                            new Offer(
                                    mock.getOfferId(),
                                    mock.getOfferTitle(),
                                    mock.getOfferDescription(),
                                    mock.getOfferLocation(),
                                    mock.getOfferSkills(),
                                    mock.getOfferLanguages()
                            )
                    );
                }
            } catch (JsonProcessingException error) {
                throw new RuntimeException(error.toString());
            }
        }
        return new ArrayList<>();
    }
}
