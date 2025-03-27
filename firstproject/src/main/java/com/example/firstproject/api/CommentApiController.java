package com.example.firstproject.api;

import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentApiController {
    @Autowired
    private CommentService commentService;
    //1. Retrieve comments
    @GetMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<List<CommentDto>> comments(@PathVariable Long articleId){
        // Delegate to Service
        List<CommentDto> dtos = commentService.comments(articleId);
        // response result
        return ResponseEntity.status(HttpStatus.OK).body(dtos);

    }
    //2. Add comments
    @PostMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<CommentDto> create(@PathVariable Long articleId,
                                             @RequestBody CommentDto dto){
        // Delegate to Service
        CommentDto createdDto = commentService.create(articleId, dto);
        // response result
        return ResponseEntity.status(HttpStatus.OK).body(createdDto);
    }
    //3. Revise comments
    @PatchMapping("/api/comments/{id}")
    public ResponseEntity<CommentDto> update(@PathVariable Long id, @RequestBody CommentDto dto){
        // Delegate to Service
        CommentDto updatedDto = commentService.update(id, dto);
        // response result
        return ResponseEntity.status(HttpStatus.OK).body(updatedDto);
    }
    //4. Delete comments
    @DeleteMapping("/api/comments/{id}")
    public ResponseEntity<CommentDto> delete(@PathVariable Long id){
        // Delegate to Service
        CommentDto deletedDto = commentService.delete(id);
        // response result
        return ResponseEntity.status(HttpStatus.OK).body(deletedDto);
    }
}
