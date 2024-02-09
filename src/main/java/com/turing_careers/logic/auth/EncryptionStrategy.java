package com.turing_careers.logic.auth;

/**
 * @author Antonino Lorenzo
 * */
public interface EncryptionStrategy {
    public String encrypt(String str);
    public void verify(String raw, String original) throws InvalidCredentialsException;
}
