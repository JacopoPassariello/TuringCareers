package com.turing_careers.domain.search;

import com.turing_careers.data.model.*;

import java.util.ArrayList;
import java.util.List;

public class ClientFactory {
    private ClientType clientType;


    public ClientFactory setType(ClientType type) {
        this.clientType = type;
        return this;
    }

    public RecommenderEngine getRecommenderEngine() { 
        return new RecommenderEngine(this.clientType);
    }

    public UpdateEngine getUpdateEngine() {
        return new UpdateEngine(this.clientType);
    }

    public static void main(String[] args) {
        Skill skill = new Skill("Python", "Programming Language");
        List<Skill> skills = new ArrayList<>();
        skills.add(skill);

        Language lang = new Language("it");
        List<Language> languages = new ArrayList<>();
        languages.add(lang);


        Developer dev = new Developer(
                    1L,
                    "Antonino",
                    "Lorenzo",
                    "bio",
                    "anton@gmail.com",
                    "1234",
                    new Location(),
                    skills,
                    languages
                );

        List<Offer> offers = (List<Offer>) new ClientFactory()
                .setType(ClientType.OFFER)
                .getRecommenderEngine()
                .search("Web Developer", dev);
    }
}
