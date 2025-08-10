package com.forumhub.controller;

import com.forumhub.domain.topic.Topic;
import com.forumhub.domain.topic.TopicDetailsData;
import com.forumhub.domain.topic.TopicRegistrationData;
import com.forumhub.repository.TopicRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topics")
public class TopicController {

    @Autowired
    private TopicRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity createTopic(@RequestBody @Valid TopicRegistrationData topicData, UriComponentsBuilder location) {
        var topic = new Topic(topicData);
        repository.save(topic);
        var uri = location.path("/topics/{id}").buildAndExpand(topic.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicDetailsData(topic));
    }
}
