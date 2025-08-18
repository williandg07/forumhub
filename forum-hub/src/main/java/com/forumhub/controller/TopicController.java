package com.forumhub.controller;

import com.forumhub.dto.TopicRequest;
import com.forumhub.dto.TopicResponse;
import com.forumhub.entity.User;
import com.forumhub.service.AuthService;
import com.forumhub.service.TopicService;
import com.forumhub.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/topics")
@CrossOrigin(origins = "*")
public class TopicController {
    
    @Autowired
    private TopicService topicService;
    
    @Autowired
    private AuthService authService;
    
    @Autowired
    private UserService userService;
    
    @GetMapping
    public ResponseEntity<Page<TopicResponse>> getAllTopics(@PageableDefault(size = 10) Pageable pageable) {
        Page<TopicResponse> topics = topicService.getAllTopics(pageable);
        return ResponseEntity.ok(topics);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getTopicById(@PathVariable Long id) {
        Optional<TopicResponse> topic = topicService.getTopicById(id);
        if (topic.isPresent()) {
            return ResponseEntity.ok(topic.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping
    public ResponseEntity<?> createTopic(@Valid @RequestBody TopicRequest topicRequest,
                                        @RequestHeader("Authorization") String token) {
        try {
            User user = getUserFromToken(token);
            if (user == null) {
                return ResponseEntity.status(401).body("Token inválido");
            }
            
            TopicResponse response = topicService.createTopic(topicRequest, user);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao criar tópico: " + e.getMessage());
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateTopic(@PathVariable Long id,
                                        @Valid @RequestBody TopicRequest topicRequest,
                                        @RequestHeader("Authorization") String token) {
        try {
            User user = getUserFromToken(token);
            if (user == null) {
                return ResponseEntity.status(401).body("Token inválido");
            }
            
            Optional<TopicResponse> response = topicService.updateTopic(id, topicRequest, user);
            if (response.isPresent()) {
                return ResponseEntity.ok(response.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao atualizar tópico: " + e.getMessage());
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTopic(@PathVariable Long id,
                                        @RequestHeader("Authorization") String token) {
        try {
            User user = getUserFromToken(token);
            if (user == null) {
                return ResponseEntity.status(401).body("Token inválido");
            }
            
            boolean deleted = topicService.deleteTopic(id, user);
            if (deleted) {
                return ResponseEntity.ok("Tópico deletado com sucesso");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao deletar tópico: " + e.getMessage());
        }
    }
    
    private User getUserFromToken(String authHeader) {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            if (authService.validateToken(token)) {
                String username = authService.getUsernameFromToken(token);
                return userService.findByUsername(username).orElse(null);
            }
        }
        return null;
    }
}