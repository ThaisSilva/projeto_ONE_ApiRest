package com.alura.challenge.backend.controller;

import com.alura.challenge.backend.dto.TopicDTO;
import com.alura.challenge.backend.dto.TopicResponseDTO;
import com.alura.challenge.backend.dto.UpdateTopicDTO;
import com.alura.challenge.backend.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topics")
public class TopicController {

    @Autowired
    private TopicService topicService;

    @PostMapping
    public void createTopic(@RequestBody @Validated TopicDTO topicDTO) {
        topicService.createTopic(topicDTO);
    }

    @PutMapping("/{id}")
    public void updateTopic(@RequestBody @Validated UpdateTopicDTO topicDTO, @PathVariable Long id) {
        topicService.updateTopic(topicDTO, id);
    }

    @GetMapping
    public ResponseEntity<List<TopicResponseDTO>> listAllTopics(
            @RequestParam(required = false) String courseName,
            @RequestParam(required = false) Integer year,
            @PageableDefault(sort = "creationDate", direction = Sort.Direction.ASC) Pageable pageable) {

        List<TopicResponseDTO> topics = topicService.listAllTopics(courseName, year, pageable);
        return ResponseEntity.ok(topics);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicResponseDTO> getTopicsById(@PathVariable Long id) {
        TopicResponseDTO topic = topicService.findTopicById(id);

        return ResponseEntity.ok(topic);
    }

    @DeleteMapping("/{id}")
    public void deleteTopic(@PathVariable Long id) {
        topicService.deleteTopicById(id);
    }
}
