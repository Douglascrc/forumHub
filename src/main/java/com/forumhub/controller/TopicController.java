package com.forumhub.controller;

import com.forumhub.domain.topic.Topic;
import com.forumhub.domain.topic.TopicDetailsData;
import com.forumhub.domain.topic.TopicRegistrationData;
import com.forumhub.repository.CourseRepository;
import com.forumhub.repository.TopicRepository;
import com.forumhub.repository.UserRepository;
import com.forumhub.service.TopicService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topics")
public class TopicController {

    @Autowired
    private TopicService topicService;

    @PostMapping
    @Transactional
    public ResponseEntity createTopic(@RequestBody @Valid TopicRegistrationData topicData, UriComponentsBuilder location) {
        var topic = topicService.createTopic(topicData);

        var uri = location.path("/topics/{id}").buildAndExpand(topic.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicDetailsData(topic));
    }
}
