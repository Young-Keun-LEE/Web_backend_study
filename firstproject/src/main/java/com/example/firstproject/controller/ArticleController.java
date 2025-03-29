package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private CommentService commentService;
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
        List<CommentDto> commentsDtos = commentService.comments(id);
        //2. Register data in the model
        model.addAttribute("article", articleEntity);
        model.addAttribute("commentDtos", commentsDtos);
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
    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model){

        Article articleEntity = articleRepository.findById(id).orElse(null);
        model.addAttribute("article", articleEntity);
        return "articles/edit";
    }
    @PostMapping("/articles/update")
    public String update(ArticleForm form){
        log.info(form.toString());
        // 1. Convert DTO to Entity
        Article articleEntity = form.toEntity();
        log.info(articleEntity.toString());
        // 2. Save Entity to Database
        // 2-1. Fetch existing data from the database
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);
        // 2-2. Update existing data values
        if(target != null){
            articleRepository.save(articleEntity);
        }
        // 3. Redirect to the Update Result Page
        return "redirect:/articles/" + articleEntity.getId();
    }

    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){
        log.info("The delete request is required!");
        // 1. Fetch the target to be deleted
        Article target = articleRepository.findById(id).orElse(null);
        log.info(target.toString());
        // 2. Delete the target entity
        if(target != null){
            articleRepository.delete(target);
            rttr.addFlashAttribute("msg", "Data is deleted!");
        }
        // 3. Redirect to the result page
        return "redirect:/articles";
    }
   }