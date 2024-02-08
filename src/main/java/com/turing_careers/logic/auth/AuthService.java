package com.turing_careers.logic.auth;

import com.turing_careers.data.model.User;

/**
 *
 * */
public abstract class AuthService {
    protected EncryptionStrategy encryptionStrategy;

    /**
     *
     * */
    public abstract void loginUser(String email, String password) throws InvalidCredentialsException;


    /**
     *
     * */
    public abstract void signupUser(User user) throws Exception;

    /**
     *
     * */
    public void setEncryptionStrategy(EncryptionStrategy strategy) {
        this.encryptionStrategy = strategy;
    }
}
