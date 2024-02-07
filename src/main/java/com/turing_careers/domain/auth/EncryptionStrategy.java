package com.turing_careers.domain.auth;

public interface EncryptionStrategy {
    public String encrypt(String str);
    public void verify(String raw, String original) throws InvalidCredentialsException;
}
