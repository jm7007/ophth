package jm.ophthalmic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommunityController {
    
    @GetMapping("community/review")
    public String review(Model model){
        model.addAttribute("content","review/review");
        return "community";
    }
    @GetMapping("review/new")
    public String reviewNew(){
        return "review/reviewnew";
    }
}
