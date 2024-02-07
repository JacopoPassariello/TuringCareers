package com.turing_careers.domain.search;

import com.turing_careers.data.model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Only class a Client should interact with directly, creates a RecommenderEngine or UpdateEngine
 * based on the ClientType specified; users must set a ClientType that can be OFFER or DEVELOPER.
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
