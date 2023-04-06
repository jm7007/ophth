package jm.ophthalmic.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import jm.ophthalmic.controller.form.LoginForm;
import jm.ophthalmic.controller.form.UserForm;
import jm.ophthalmic.domain.User;
import jm.ophthalmic.service.UserService;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("join")
    public String join(Model model) {
        return "join";
    }

    @PostMapping("join/new")
    public String createAccount(UserForm userForm) {
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
        System.out.printf("new user = id: %d | name: %s", user.getId(), user.getName());
        return "redirect:/";
    }

    @GetMapping("login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("login")
    public String login(LoginForm loginForm, HttpSession session) throws Exception {
        Optional<User> user = userService.login(loginForm);
        // 로그인 실패
        if (user == null) {
            return "redirect:/loginfail";
        }
        // 로그인 성공
        session.setAttribute("account", user.get().getAccount());
        session.setAttribute("admin", user.get().getAdmin());
        System.out.println("login success | account:" + session.getAttribute("account"));
        return "redirect:/";
    }

    @GetMapping("logout")
    public String logout(HttpSession session) {
        session.removeAttribute("account");
        session.removeAttribute("admin");
        return "redirect:/";
    }
    @GetMapping("loginfail")
    public String loginFail(){
        return "exception/loginfail";
    }
}
