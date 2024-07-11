package com.alura.challenge.backend.entities;

import com.alura.challenge.backend.dto.UpdateTopicDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Data
@Entity
@Table(name = "TOPIC")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "title")
    private String title;

    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "message")
    private String message;

    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "status")
    private String status;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToMany(mappedBy = "topic")
    private List<Answer> answers;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creation_date")
    private LocalDateTime creationDate;


    public void toEntity(UpdateTopicDTO topicDTO) {
        if (isNotBlank(topicDTO.getTitle())) {
            this.title = topicDTO.getTitle();
        }
        if (isNotBlank(topicDTO.getMessage())) {
            this.message = topicDTO.getMessage();
        }
        if (isNotBlank(topicDTO.getStatus())) {
            this.status = topicDTO.getStatus();
        }
    }
}