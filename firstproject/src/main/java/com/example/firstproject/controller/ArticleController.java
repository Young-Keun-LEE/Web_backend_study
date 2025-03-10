package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.ArrayList;
@Slf4j
@Controller
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;
    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "articles/new";
    }


    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form){
        log.info(form.toString());
        //System.out.println(form.toString());
        //1. Convert DTO to Entity
        Article article = form.toEntity();
        log.info(article.toString());
        //System.out.println(article.toString());
        //2. Save Entity to DB through Repository
        Article saved = articleRepository.save(article);
        log.info(saved.toString());
        //System.out.println(saved.toString());
        return "redirect:/articles/" + saved.getId();
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model){
        log.info("id = " + id);
        //1.Query the ID to retrieve data.
        Article articleEntity = articleRepository.findById(id).orElse(null);
        //2. Register data in the model
        model.addAttribute("article", articleEntity);
        //3. Return view page
        return "articles/show";
    }
    @GetMapping("/articles")
    public String index(Model model){
    //1. Retrieve all data
    ArrayList<Article> articleEntityList = articleRepository.findAll();
    //2. Register data in the model
    model.addAttribute("articleList", articleEntityList);
    //3. Configure view page
        return "articles/index";
    }
   }