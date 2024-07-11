package com.alura.challenge.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateTopicException.class)
    ProblemDetail handleDuplicateTopicException(DuplicateTopicException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, e.getLocalizedMessage());
        problemDetail.setTitle("Duplicate Topic");
        problemDetail.setDetail("A topic with the same title and message already exists.");
        problemDetail.setProperty("Category", "Validation");
        problemDetail.setProperty("Timestamp", Instant.now());
        return problemDetail;
    }

    @ExceptionHandler(InvalidTopicDataException.class)
    ProblemDetail handleInvalidTopicDataException(InvalidTopicDataException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
        problemDetail.setTitle("Invalid Topic Data");
        problemDetail.setDetail("The provided topic data is invalid.");
        problemDetail.setProperty("Category", "Validation");
        problemDetail.setProperty("Timestamp", Instant.now());
        return problemDetail;
    }

    @ExceptionHandler(TopicNotFoundException.class)
    ProblemDetail handleTopicNotFoundException(TopicNotFoundException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getLocalizedMessage());
        problemDetail.setTitle("Topic was not found");
        problemDetail.setDetail("Topic was not found by id.");
        problemDetail.setProperty("Topic", "Validation");
        problemDetail.setProperty("Timestamp", Instant.now());
        return problemDetail;
    }

    @ExceptionHandler(UserNotFoundException.class)
    ProblemDetail handleUserNotFoundException(UserNotFoundException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getLocalizedMessage());
        problemDetail.setTitle("User was not found");
        problemDetail.setDetail("User was not found by id.");
        problemDetail.setProperty("Topic", "Validation");
        problemDetail.setProperty("Timestamp", Instant.now());
        return problemDetail;
    }
}