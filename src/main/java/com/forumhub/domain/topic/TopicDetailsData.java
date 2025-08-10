package com.forumhub.domain.topic;

public record TopicDetailsData(
        String title,
        String message,
        String authorName,
        String courseName,
        String status
) {

    public TopicDetailsData(Topic topic) {
        this(
                topic.getTitle(),
                topic.getMessage(),
                topic.getAuthor().getName(),
                topic.getCourse().getName(),
                topic.getStateTopic().name()
        );
    }
}
