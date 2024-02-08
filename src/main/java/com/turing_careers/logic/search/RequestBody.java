package com.turing_careers.logic.search;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.turing_careers.data.model.Item;
import com.turing_careers.data.model.User;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class RequestBody {
    private String jsonRequest;

    public RequestBody(Item item) {
        this.jsonRequest = RequestBody.toJSON(item);
    }

    public RequestBody(String query) {
        this.jsonRequest = RequestBody.toJSON(query);
    }

    public RequestBody(String query, User user) {
        List<Object> combinedData = new ArrayList<>();
        combinedData.add(query);
        combinedData.add(user);
        this.jsonRequest = toJSON(combinedData);
    }

    private static String toJSON(Object obj) {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = null;
        try {
            jsonString = objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonString;
    }
}
