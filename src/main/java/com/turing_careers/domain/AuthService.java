package com.turing_careers.domain;

import com.turing_careers.data.model.User;

/**
 *
 * */
public abstract class AuthService {
    private EncryptionStrategy encryptionStrategy;
    public abstract void loginUser(String email, String password);

    public abstract void signupUser(User user);
    public void setEncryptionStrategy(EncryptionStrategy strategy) {
        this.encryptionStrategy = strategy;
    }
}
