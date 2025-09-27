package com.example.userauthservice.Exception;

public class PasswordMissMatch extends RuntimeException {
    public PasswordMissMatch(String message) {
        super(message);
    }
}
