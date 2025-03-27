package com.example.firstproject.repository;

import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CommentRepositoryTest {
    @Autowired
    CommentRepository commentRepository;
    @Test
    @DisplayName("Retrieve all comments for a specific post")
    void findByArticleId() {
        // Case 1: Retrieve all comments for post number 4
        {
            // 1. Prepare input data
            Long articleId = 4L;
            // 2. Actual data
            List<Comment> comments = commentRepository.findByArticleId(articleId);
            // 3. Expected data
            Article article = new Article(4L, "What is your best movie?", "comment plz");
            Comment a = new Comment(1L, article,"Park", "Avengers");
            Comment b = new Comment(2L, article, "Kim", "Matrix");
            Comment c = new Comment(3L, article, "Choi", "Real Steal");
            List<Comment> expected = Arrays.asList(a,b,c);
            // 4. Comparison and verification
            assertEquals(expected.toString(), comments.toString(), "Print all comments for post number 4!");
        }
        // Case 2: Retrieve all comments for post number 1
        {
            // 1. Prepare input data
            Long articleId = 1L;
            // 2. Actual data
            List<Comment> comments = commentRepository.findByArticleId(articleId);
            // 3. Expected data
            Article article = new Article(1L,"가가가가","1111");
            List<Comment> expected = Arrays.asList();
            // 4. Comparison and verification
            assertEquals(expected.toString(),comments.toString(),"There is no comment for post number 1");
        }
        // Case 3: Retrieve all comments for post number 9
        {
            // 1. Prepare input data
            Long articleId = 9L;
            // 2. Actual data
            List<Comment> comments = commentRepository.findByArticleId(articleId);
            // 3. Expected data
            Article article = null;
            List<Comment> expected = Arrays.asList();
            // 4. Comparison and verification
            assertEquals(comments.toString(), expected.toString(),"There is no article");
        }
    }
    @Test
    @DisplayName("Retrieve all comments by a specific nickname")
    void findByNickname(){
        //Case 1: Retrieve all comments by "Park"
        {
            // 1. Prepare input data
            String nickname = "Park";
            // 2. Actual data
            List<Comment> comments = commentRepository.findByNickname(nickname);
            // 3. Expected data
            Comment a = new Comment(1L, new Article(4L, "What is your best movie?", "comment plz"), nickname, "Avengers");
            Comment b = new Comment(4L, new Article(5L, "What is your favorite food?", "comment plz plz"), nickname, "Fried Chicken");
            Comment c = new Comment(7L, new Article(6L, "What do you do for fun?", "comment plz plz plz"), nickname, "Workout");
            List<Comment> expected = Arrays.asList(a, b, c);
            // 4. Comparison and verification
            assertEquals(expected.toString(), comments.toString());
        }
        //Case 2: Retrieve all comments by "Kim"
        {
            // 1. Prepare input data
            String nickname = "Kim";
            // 2. Actual data
            List<Comment> comments = commentRepository.findByNickname(nickname);
            // 3. Expected data
            Comment a = new Comment(2L, new Article(4L, "What is your best movie?", "comment plz"), nickname, "Matrix");
            Comment b = new Comment(5L, new Article(5L, "What is your favorite food?", "comment plz plz"), nickname, "Hamburger");
            Comment c = new Comment(8L, new Article(6L, "What do you do for fun?", "comment plz plz plz"),nickname, "Watching Youtube");
            List<Comment> expected = Arrays.asList(a,b,c);
            // 4. Comparison and verification
            assertEquals(comments.toString(), expected.toString());
        }
        //Case 3: Retrieve all comments by "Null"
        {
            // 1. Prepare input data
            String nickname = null;
            // 2. Actual data
            List<Comment> comments = commentRepository.findByNickname(nickname);
            // 3. Expected data
            List<Comment> expected = Arrays.asList();
            // 4. Comparison and verification
            assertEquals(comments.toString(), expected.toString());
        }
        //Case 4: Retrieve all comments by ""
        {
            // 1. Prepare input data
            String nickname = "";
            // 2. Actual data
            List<Comment> comments = commentRepository.findByNickname(nickname);
            // 3. Expected data
            List<Comment> expected = Arrays.asList();
            // 4. Comparison and verification
            assertEquals(comments.toString(), expected.toString());
        }

    }
}