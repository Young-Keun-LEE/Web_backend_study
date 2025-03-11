package com.example.firstproject.controller;

import com.example.firstproject.dto.MemberForm;
import com.example.firstproject.entity.Member;
import com.example.firstproject.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;


@Slf4j
@Controller
public class MemberController {
    @Autowired
    MemberRepository memberRepository;
    @GetMapping("/signup")
    public String signUpPage(){
        return "members/new";
    }

    @GetMapping("/members/new")
    public String newMemberForm(){
        return "members/new";
    }
    @PostMapping("/join")
    public String join(MemberForm memberForm){
        log.info(memberForm.toString());
        //1. Convert DTO to Entity
        Member member = memberForm.toEntity();
        log.info(member.toString());
        //2. Save Entity to DB through Repository
        Member saved = memberRepository.save(member);
        log.info(saved.toString());
        return "redirect:/members/" + saved.getId();

    }

    @GetMapping("/members/{id}")
    public String show(@PathVariable Long id, Model model){
        Member memberEntity = memberRepository.findById(id).orElse(null);
        model.addAttribute("member",memberEntity);
        return "/members/show";
    }

    @GetMapping("/members")
    public String index(Model model){
        Iterable <Member> memberEntityList = memberRepository.findAll();
        model.addAttribute("memberList", memberEntityList);
        return "/members/index";
    }

    @GetMapping("/members/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        Member memberEntity = MemberRepository.findById(id).orElse(null);
        model.addAttribute("member", memberEntity);
        return "/members/edit";
    }
}
