package com.forumhub.service;

import com.forumhub.domain.topic.Topic;
import com.forumhub.domain.topic.TopicRegistrationData;
import com.forumhub.repository.CourseRepository;
import com.forumhub.repository.TopicRepository;
import com.forumhub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private TopicRepository topicRepository;

    public Topic createTopic(TopicRegistrationData topicData) {
        var topic = new  Topic(topicData);
        var author = userRepository.getReferenceById(topicData.idAutor());
        var course = courseRepository.getReferenceById(topicData.idCurso());

        topic.setAuthor(author);
        topic.setCourse(course);

        topicRepository.save(topic);

        return topic;
    }
}
