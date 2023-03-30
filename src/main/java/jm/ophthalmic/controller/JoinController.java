package jm.ophthalmic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jm.ophthalmic.domain.User;
import jm.ophthalmic.service.UserService;

@Controller
public class JoinController {

    private final UserService userService;
    
    public JoinController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("join")
    public String join(Model model){
        return "join";
    }
    @PostMapping("join/new")
    public String createAccount(UserForm userForm){
        System.out.println(userForm);
        User user = new User();
        user.setAccount(userForm.getAccount());
        user.setPassword(userForm.getPassword());
        user.setEmail(userForm.getEmail());
        user.setName(userForm.getName());
        user.setContact(userForm.getContact());
        user.setGender(userForm.getGender());
        user.setEmail(userForm.getEmail());
        user.setBirth(userForm.getBirth());
        System.out.println(user);
        userService.join(user);
        System.out.printf("new user = id: %d | name: %s",user.getId(),user.getName());
        return "redirect:/";
    }
    
}
