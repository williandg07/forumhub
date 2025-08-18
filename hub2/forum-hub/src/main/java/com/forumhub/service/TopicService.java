package com.forumhub.service;

import com.forumhub.dto.TopicRequest;
import com.forumhub.dto.TopicResponse;
import com.forumhub.entity.Topic;
import com.forumhub.entity.User;
import com.forumhub.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class TopicService {
    
    @Autowired
    private TopicRepository topicRepository;
    
    public TopicResponse createTopic(TopicRequest request, User author) {
        // Verificar se já existe um tópico com o mesmo título e mensagem
        if (topicRepository.existsByTitleAndMessage(request.getTitle(), request.getMessage())) {
            throw new RuntimeException("Já existe um tópico com este título e mensagem");
        }
        
        Topic topic = new Topic(request.getTitle(), request.getMessage(), request.getCourse(), author);
        Topic savedTopic = topicRepository.save(topic);
        return new TopicResponse(savedTopic);
    }
    
    public Page<TopicResponse> getAllTopics(Pageable pageable) {
        Page<Topic> topics = topicRepository.findAllOrderByCreationDateDesc(pageable);
        return topics.map(TopicResponse::new);
    }
    
    public Optional<TopicResponse> getTopicById(Long id) {
        return topicRepository.findById(id).map(TopicResponse::new);
    }
    
    public Optional<TopicResponse> updateTopic(Long id, TopicRequest request, User user) {
        Optional<Topic> topicOpt = topicRepository.findById(id);
        if (topicOpt.isEmpty()) {
            return Optional.empty();
        }
        
        Topic topic = topicOpt.get();
        if (!topic.getAuthor().getId().equals(user.getId())) {
            throw new RuntimeException("Apenas o autor pode editar o tópico");
        }
        
        topic.setTitle(request.getTitle());
        topic.setMessage(request.getMessage());
        topic.setCourse(request.getCourse());
        
        Topic updatedTopic = topicRepository.save(topic);
        return Optional.of(new TopicResponse(updatedTopic));
    }
    
    public boolean deleteTopic(Long id, User user) {
        Optional<Topic> topicOpt = topicRepository.findById(id);
        if (topicOpt.isEmpty()) {
            return false;
        }
        
        Topic topic = topicOpt.get();
        if (!topic.getAuthor().getId().equals(user.getId())) {
            throw new RuntimeException("Apenas o autor pode deletar o tópico");
        }
        
        topicRepository.delete(topic);
        return true;
    }
}