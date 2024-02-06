package com.turing_careers.domain;

import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

public class Argon2Encryption implements EncryptionStrategy {

    private final Argon2PasswordEncoder encoder;

    public Argon2Encryption() {
        // default parameters
        int saltLength =  16; // Length of the salt
        int hashLength =  32; // Length of the hash
        int parallelism =  4; // Number of threads to use for hashing
        int memory =  65536; // Memory cost in kilobytes
        int iterations =  3; // Number of iterations

        this.encoder = new Argon2PasswordEncoder(
                saltLength,
                hashLength,
                parallelism,
                memory,
                iterations
        );
    }

    @Override
    public String encrypt(String str) {
        return this.encoder.encode(str);
    }

}
