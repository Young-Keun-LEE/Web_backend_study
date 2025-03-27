package com.example.firstproject.repository;

import com.example.firstproject.entity.Comment;
import org.hibernate.annotations.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    // Retrieve all comments for a specific post
    @Query(value = "SELECT * FROM comment WHERE article_id = :articleId", nativeQuery = true)
    List<Comment> findByArticleId(@Param("articleId") Long articleId);
    // Retrieve all comments from a specific nickname
    List<Comment> findByNickname(String nickname);

}
