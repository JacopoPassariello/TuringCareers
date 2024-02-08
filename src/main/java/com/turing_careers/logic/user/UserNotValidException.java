package com.turing_careers.logic.user;

public class UserNotValidException extends Exception {
    public UserNotValidException(String msg) {
        super(msg);
    }

    public UserNotValidException() {

    }
}
