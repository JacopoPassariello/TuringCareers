package com.turing_careers.domain.search;

import com.turing_careers.data.model.Item;
import com.turing_careers.data.model.User;

import java.util.ArrayList;
import java.util.List;

public class RecommenderEngine implements Engine{
    private ClientType type;
    public RecommenderEngine(ClientType type) {

    }

    public List<Item> search(String query) {
        return new ArrayList<>();
    }

    public List<Item> search(String query, User user) {
        return new ArrayList<>();
    }
}
