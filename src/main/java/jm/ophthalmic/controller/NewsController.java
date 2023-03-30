package jm.ophthalmic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NewsController {
    
    @GetMapping("news")
    public String join(Model model){
        return "news";
    }
    
}
