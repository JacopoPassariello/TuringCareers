package com.turing_careers.logic.auth;

import com.turing_careers.data.dao.PersistenceException;
import com.turing_careers.data.model.User;

/**
 * @author Antonino Lorenzo
 * */
public abstract class Authenticator {
    protected EncryptionStrategy encryptionStrategy;

    /**
     *
     * */
    public abstract User loginUser(String email, String password) throws InvalidCredentialsException;


    /**
     *
     * */
    public abstract User signupUser(User user) throws Exception, PersistenceException;

    /**
     *
     * */
    public void setEncryptionStrategy(EncryptionStrategy strategy) {
        this.encryptionStrategy = strategy;
    }
}
