package com.turing_careers.domain;

import com.turing_careers.data.model.User;

public class DeveloperAuthService extends AuthService {
    @Override
    public void loginUser(String email, String password) {
        // 1: Cifra password tramite l'algoritmo usato
        // 2: Verifica il match nel database
    }

    @Override
    public void signupUser(User user) {

    }
}
