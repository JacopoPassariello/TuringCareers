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
    public abstract void loginUser(String email, String password) throws InvalidCredentialsException;


    /**
     *
     * */
    public abstract void signupUser(User user) throws Exception, PersistenceException;

    /**
     *
     * */
    public void setEncryptionStrategy(EncryptionStrategy strategy) {
        this.encryptionStrategy = strategy;
    }
}
