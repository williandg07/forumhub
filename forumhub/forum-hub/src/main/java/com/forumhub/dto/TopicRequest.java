package com.forumhub.dto;

import jakarta.validation.constraints.NotBlank;

public class TopicRequest {
    @NotBlank(message = "Título é obrigatório")
    private String title;
    
    @NotBlank(message = "Mensagem é obrigatória")
    private String message;
    
    @NotBlank(message = "Curso é obrigatório")
    private String course;
    
    // Constructors
    public TopicRequest() {}
    
    public TopicRequest(String title, String message, String course) {
        this.title = title;
        this.message = message;
        this.course = course;
    }
    
    // Getters e Setters
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    
    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }
}