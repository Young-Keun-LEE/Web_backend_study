package com.example.firstproject.entity;

import com.example.firstproject.dto.CommentDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.boot.autoconfigure.web.WebProperties;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="article_id")
    private Article article;
    @Column
    private String nickname;
    @Column
    private String body;

    public static Comment createComment(CommentDto dto, Article article) {
        // Exception occurs
        if (dto.getId() != null)
            throw new IllegalArgumentException("There should be no comment's id");
        if (dto.getArticleId() != article.getId())
            throw new IllegalArgumentException("Wrong article's id");
        // Create and return the entity
        return new Comment(
            dto.getId(),
            article,
            dto.getNickname(),
            dto.getBody()
        );

    }

    public void patch(CommentDto dto) {
        //Exception occurred
        if(this.id != dto.getId())
            throw new IllegalArgumentException("Failed to edit comment! Incorrect id entered.");
        //Object update
        if(dto.getNickname() != null)
            this.nickname = dto.getNickname();
        if(dto.getBody() != null)
            this.body = dto.getBody();
    }
}
