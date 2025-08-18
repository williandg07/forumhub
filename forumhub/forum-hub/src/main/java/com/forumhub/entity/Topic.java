package com.forumhub.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "topics")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    private String title;
    
    @NotBlank
    @Column(columnDefinition = "TEXT")
    private String message;
    
    @Column(name = "creation_date")
    private LocalDateTime creationDate = LocalDateTime.now();
    
    @Enumerated(EnumType.STRING)
    private StatusTopic status = StatusTopic.NAO_RESPONDIDO;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private User author;
    
    private String course;
    
    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Reply> replies;
    
    // Constructors
    public Topic() {}
    
    public Topic(String title, String message, String course, User author) {
        this.title = title;
        this.message = message;
        this.course = course;
        this.author = author;
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
    
    public User getAuthor() { return author; }
    public void setAuthor(User author) { this.author = author; }
    
    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }
    
    public List<Reply> getReplies() { return replies; }
    public void setReplies(List<Reply> replies) { this.replies = replies; }
}

