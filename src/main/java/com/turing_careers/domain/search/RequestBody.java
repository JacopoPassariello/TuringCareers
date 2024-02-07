package com.turing_careers.domain.search;

import com.turing_careers.data.model.Item;
import com.turing_careers.data.model.User;

public class RequestBody {

    public RequestBody(Item item) {

    }

    public RequestBody(String query) {

    }

    public RequestBody(String query, User user) {

    }

    public void getJSON() {
        // doverbbe ritornare un oggetto json di Jackson
    }
}
