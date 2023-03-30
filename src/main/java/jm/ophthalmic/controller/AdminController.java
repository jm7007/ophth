package jm.ophthalmic.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jm.ophthalmic.domain.User;
import jm.ophthalmic.service.UserService;

@Controller
public class AdminController {

    private final UserService userService;
    
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String admin(Model model){
        List<User> users = userService.findUsers();
        model.addAttribute("users", users);
        return "admin";
    }
}
