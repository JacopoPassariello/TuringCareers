package com.turing_careers.domain.search;

public class ClientBuilder {
    private ClientType clientType;
    public ClientBuilder(ClientType type) {
        this.clientType = type;
    }

    public Engine getEngine(EngineType type) {
        if (type == EngineType.RECOMMEND)
            return new RecommenderEngine(this.clientType);
        return new UpdateEngine(this.clientType);
    }
}
