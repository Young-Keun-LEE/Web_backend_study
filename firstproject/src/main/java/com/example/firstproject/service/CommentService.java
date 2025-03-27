package com.example.firstproject.service;

import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.repository.CommentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ArticleRepository articleRepository;
    public List<CommentDto> comments(Long articleId){
//        //1. Retrieve comments
//        List<Comment> comments = commentRepository.findByArticleId(articleId);
//        //2. Convert entity to DTO
//        List<CommentDto> dtos = new ArrayList<CommentDto>();
//        for(int i = 0; i < comments.size(); i++){
//            Comment c = comments.get(i);
//            CommentDto dto = CommentDto.createCommentDto(c);
//            dtos.add(dto);
//        }
//        //3. Response results
//        return dtos;
        return commentRepository.findByArticleId(articleId)
                .stream()
                .map(comment -> CommentDto.createCommentDto(comment))
                .collect(Collectors.toList());
    }

    @Transactional
    public CommentDto create(Long articleId, CommentDto dto) {
        // 1. Retrieve the post and handle exceptions.
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("Fail to create comment! " + "There is no article"));
        // 2. Create a comment entity.
        Comment comment = Comment.createComment(dto, article);
        // 3. Save the comment entity to the database.
        Comment created = commentRepository.save(comment);
        // 4. Convert to DTO and return.
        return CommentDto.createCommentDto(created);

    }

    @Transactional
    public CommentDto update(Long id, CommentDto dto) {
        // 1. Retrieve the post and handle exceptions.
        Comment target = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Fail to revise comment" + "There is no comment"));
        // 2. Revise a comment entity.
        target.patch(dto);
        // 3. Save the comment entity to the database.
        Comment updated = commentRepository.save(target);
        // 4. Convert to DTO and return.
        return CommentDto.createCommentDto(updated);

    }

    @Transactional
    public CommentDto delete(Long id) {
        //1. Retrieve comments and handle exceptions
        Comment target = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Fail to delete comment! " + "There is no target"));
        //2. Delete comment
        commentRepository.delete(target);
        //3. Convert and return deleted comment as DTO
        return CommentDto.createCommentDto(target);
    }
}
