package com.turing_careers.logic.service.search;

/**
 * Only class a Client should interact with directly, creates a RecommenderEngine or UpdateEngine
 * based on the ClientType specified; users must set a ClientType that can be OFFER or DEVELOPER.
 * @author Antonino Lorenzo
 * */
public class ClientFactory {
    private ClientType clientType = null;

    public ClientFactory setType(ClientType type) {
        this.clientType = type;
        return this;
    }

    public RecommenderEngine getRecommenderEngine() {
        if (this.clientType == null)
            throw new RuntimeException("ClientFactory: must set a ClientType");
        return new RecommenderEngine(this.clientType);
    }

    public UpdateEngine getUpdateEngine() {
        if (this.clientType == null)
            throw new RuntimeException("ClientFactory: must set a ClientType");
        return new UpdateEngine(this.clientType);
    }
}
