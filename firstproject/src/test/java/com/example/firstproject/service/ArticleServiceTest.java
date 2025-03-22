package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ArticleServiceTest {
    @Autowired
    ArticleService articleService;
    @Test
    void index() {
        //1. Expected Data
        Article a = new Article(1L,"가가가가", "1111");
        Article b = new Article(2L, "나나나나", "2222");
        Article c = new Article(3L, "다다다다", "3333");
        List<Article> expected = new ArrayList<Article>(Arrays.asList(a, b, c));
        //2. Actual Data
        List<Article> articles = articleService.index();
        //3. Comparison and Verification
        assertEquals(expected.toString(), articles.toString());
    }

    @Test
    void show_success_exist_id_input() {
        //1. Expected Data
        Long id = 1L;
        Article expected = new Article(id, "가가가가", "1111");
        //2. Actual Data
        Article article = articleService.show(id);
        //3. Comparison and Verification
        assertEquals(expected.toString(), article.toString());
    }
    @Test
    void show_failure_not_exist_id_input() {
        //1. Expected Data
        Long id = -1L;
        Article expected = null;
        //2. Actual Data
        Article article = articleService.show(id);
        //3. Comparison and Verification
        assertEquals(expected, article);
    }

    @Test
    @Transactional
    void create_success_with_title_and_content_dto_input() {
        //1. Expected Data
        String title = "라라라라";
        String content = "4444";
        ArticleForm dto = new ArticleForm(null, title, content);
        Article expected = new Article(4L, title, content);
        //2. Actual Data
        Article article = articleService.create(dto);
        //3. Comparison and Verification
        assertEquals(expected.toString(), article.toString());
    }
    @Test
    @Transactional
    void create_failure_with_id_contained_dto_input() {
        //1. Expected Data
        Long id = 4L;
        String title = "라라라라";
        String content = "4444";
        ArticleForm dto = new ArticleForm(id, title, content);
        Article expected = null;
        //2. Actual Data
        Article article = articleService.create(dto);
        //3. Comparison and Verification
        assertEquals(expected, null);
    }

    @Test
    @Transactional
    void update_success_with_exist_id_and_title_content_dto_input() {
        //1. Expected Data
        Long id = 1L;
        String title = "가나다라";
        String content = "1234";
        ArticleForm dto = new ArticleForm(id, title, content);
        Article expected = new Article(id, title, content);
        //2. Actual Data
        Article article = articleService.update(id, dto);
        //3. Comparison and Verification
        assertEquals(expected.toString(), article.toString());
    }
    @Test
    @Transactional
    void update_success_with_exist_id_content_dto_input() {
        //1. Expected Data
        Long id = 1L;
        String title = "가나다라";
        String content = "1234";
        ArticleForm dto = new ArticleForm(id, title, content);
        Article expected = new Article(id, title, content);
        //2. Actual Data
        Article article = articleService.update(id, dto);
        //3. Comparison and Verification
        assertEquals(expected.toString(), article.toString());
    }
    @Test
    @Transactional
    void update_failure_not_exist_id_dto_input() {

        //1. Expected Data
        Long id = -1L;
        String title = "가나다라";
        String content = "1234";
        ArticleForm dto = new ArticleForm(id, title, content);
        Article expected = new Article(id, title, content);
        //2. Actual Data
        Article article = articleService.update(id, dto);
        //3. Comparison and Verification
        assertEquals(expected, article);
    }

    @Test
    @Transactional
    void delete_success_exist_id_input() {

        //1. Expected Data
        Long id = 1L;

        //2. Actual Data
        //3. Comparison and Verification
    }
    @Test
    @Transactional
    void delete_failure_not_exist_id_input() {

        //1. Expected Data
        //2. Actual Data
        //3. Comparison and Verification
    }

}