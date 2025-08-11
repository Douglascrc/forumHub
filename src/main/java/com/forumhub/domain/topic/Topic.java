package com.forumhub.domain.topic;

import com.forumhub.domain.answer.Answer;
import com.forumhub.domain.curso.Course;
import com.forumhub.domain.user.User;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "Topic")
@Table(name = "topics")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of= "id")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String message;

    @Column(name = "state_topic")
    @Enumerated(EnumType.STRING)
    private StatusTopic stateTopic;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private User author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToMany(mappedBy = "topic")
    private List<Answer> answers;

    public Topic(@Valid TopicRegistrationData topicData) {
        this.title = topicData.title();
        this.message = topicData.message();
        this.createdAt = LocalDateTime.now();
        this.stateTopic = StatusTopic.OPEN;
    }

}
