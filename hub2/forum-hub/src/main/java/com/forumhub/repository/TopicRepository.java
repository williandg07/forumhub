package com.forumhub.repository;

import com.forumhub.entity.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
    
    @Query("SELECT t FROM Topic t ORDER BY t.creationDate DESC")
    Page<Topic> findAllOrderByCreationDateDesc(Pageable pageable);
    
    @Query("SELECT t FROM Topic t WHERE t.course = :course ORDER BY t.creationDate DESC")
    Page<Topic> findByCourseOrderByCreationDateDesc(String course, Pageable pageable);
    
    boolean existsByTitleAndMessage(String title, String message);
}