package com.forumhub.dto;

import com.forumhub.entity.StatusTopic;
import com.forumhub.entity.Topic;
import java.time.LocalDateTime;

public class TopicResponse {
    private Long id;
    private String title;
    private String message;
    private LocalDateTime creationDate;
    private StatusTopic status;
    private String authorUsername;
    private String course;
    
    // Constructor
    public TopicResponse() {}
    
    public TopicResponse(Topic topic) {
        this.id = topic.getId();
        this.title = topic.getTitle();
        this.message = topic.getMessage();
        this.creationDate = topic.getCreationDate();
        this.status = topic.getStatus();
        this.authorUsername = topic.getAuthor().getUsername();
        this.course = topic.getCourse();
    }
    
    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    
    public LocalDateTime getCreationDate() { return creationDate; }
    public void setCreationDate(LocalDateTime creationDate) { this.creationDate = creationDate; }
    
    public StatusTopic getStatus() { return status; }
    public void setStatus(StatusTopic status) { this.status = status; }
    
    public String getAuthorUsername() { return authorUsername; }
    public void setAuthorUsername(String authorUsername) { this.authorUsername = authorUsername; }
    
    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }
}