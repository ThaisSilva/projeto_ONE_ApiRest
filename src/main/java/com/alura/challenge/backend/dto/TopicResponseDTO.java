package com.alura.challenge.backend.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TopicResponseDTO {

    private String title;
    private String message;
    private LocalDateTime creationDate;
    private String status;
    private String authorName;
    private String courseName;
}
