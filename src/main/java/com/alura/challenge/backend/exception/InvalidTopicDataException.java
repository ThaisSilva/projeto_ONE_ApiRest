package com.alura.challenge.backend.exception;

public class InvalidTopicDataException extends RuntimeException {
    public InvalidTopicDataException(String message) {
        super(message);
    }
}
