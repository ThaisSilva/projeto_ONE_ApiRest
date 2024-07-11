package com.alura.challenge.backend.service;

import com.alura.challenge.backend.dto.TopicDTO;
import com.alura.challenge.backend.dto.TopicResponseDTO;
import com.alura.challenge.backend.dto.UpdateTopicDTO;
import com.alura.challenge.backend.entities.Course;
import com.alura.challenge.backend.entities.Topic;
import com.alura.challenge.backend.entities.User;
import com.alura.challenge.backend.exception.DuplicateTopicException;
import com.alura.challenge.backend.exception.InvalidTopicDataException;
import com.alura.challenge.backend.exception.TopicNotFoundException;
import com.alura.challenge.backend.repository.CourseRepository;
import com.alura.challenge.backend.repository.TopicRepository;
import com.alura.challenge.backend.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Transactional
    public void createTopic(TopicDTO topicDTO) {
        Optional<User> author = userRepository.findById(topicDTO.getAuthorId());
        Optional<Course> course = courseRepository.findById(topicDTO.getCourseId());

        if (author.isEmpty() || course.isEmpty()) {
            throw new InvalidTopicDataException("Invalid author or course ID");
        }

        if (topicRepository.findByTitleAndMessage(topicDTO.getTitle(), topicDTO.getMessage()).isPresent()) {
            throw new DuplicateTopicException("Topic with the same title and message already exists");
        }

        Topic topic = new Topic();
        topic.setTitle(topicDTO.getTitle());
        topic.setMessage(topicDTO.getMessage());
        topic.setAuthor(author.get());
        topic.setCourse(course.get());
        topic.setCreationDate(LocalDateTime.now());
        topic.setStatus(topicDTO.getStatus());

        topicRepository.save(topic);
    }

    @Transactional
    public void updateTopic(UpdateTopicDTO userDTO, Long id) {
        Topic topic = topicRepository.findById(id)
                .orElseThrow(() -> new TopicNotFoundException("Topic was not found by id"));

        topic.toEntity(userDTO);

        topicRepository.save(topic);
    }

    public List<TopicResponseDTO> listAllTopics(String courseName, Integer year, Pageable pageable) {
        Page<Topic> topics;
        if (courseName != null && year != null) {
            topics = topicRepository.findByCourseNameAndYear(courseName.toLowerCase(), year, pageable);
        } else {
            topics = topicRepository.findAll(pageable);
        }
        return topics.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public TopicResponseDTO findTopicById(Long id) {
        Topic topic = topicRepository.findById(id).orElseThrow(() ->
                new TopicNotFoundException(String.format("Topic with id %s was not found", id))
        );

        return convertToDTO(topic);
    }

    @Transactional
    public void deleteTopicById(Long id) {
        topicRepository.deleteById(id);
    }

    private TopicResponseDTO convertToDTO(Topic topic) {
        TopicResponseDTO dto = new TopicResponseDTO();
        dto.setTitle(topic.getTitle());
        dto.setMessage(topic.getMessage());
        dto.setCreationDate(topic.getCreationDate());
        dto.setStatus(topic.getStatus());
        dto.setAuthorName(topic.getAuthor().getName());
        dto.setCourseName(topic.getCourse().getName());
        return dto;
    }
}
