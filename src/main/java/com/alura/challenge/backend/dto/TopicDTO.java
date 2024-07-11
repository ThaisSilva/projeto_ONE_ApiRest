package com.alura.challenge.backend.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Valid
@Data
public class TopicDTO {

    @NotNull
    @Size(min = 1, max = 100)
    private String title;

    @NotNull
    @Size(min = 1, max = 1000)
    private String message;

    @NotNull
    private String status;

    @NotNull
    private Long authorId;

    @NotNull
    private Long courseId;

}