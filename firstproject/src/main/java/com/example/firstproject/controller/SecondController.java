package com.example.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecondController {
    @GetMapping("/random-quote")
    public String randomQuote(Model model){
        String[] quotes = {
                "매일의 역사를 쓰라. " +
                        "-프리드리히 니체-",
                "인생은 자전거 타기와 같다 균형을 잡으려면 앞으로 나아가야한다."+
                        "-알베르트 아인슈타인-",
                "나는 거인의 어깨위에 앉았기 때문에 더 멀리 내다 볼 수 있었다"+
                        "-아이작 뉴턴-",
                "세상을 바꿀 수 있다고 생각하는 제대로 정신 나간 사람들이 세상을 변화시킨다."+
                        "-스티브 잡스-"
        };
        int randInt = (int)(Math.random() * quotes.length);
        model.addAttribute("randomQuote", quotes[randInt]);
        return "quote";
    }

}
