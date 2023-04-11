package jm.ophthalmic.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

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
    public String createAccount(UserForm userForm,HttpSession session) {
        try{
        userService.join(userService.convertForm(userForm));
        return "redirect:/";
        }catch(IllegalStateException e){
            session.setAttribute("msg", e.getMessage());
            return "redirect:/joinfail";
        }
    }
    @GetMapping("joinfail")
    @ResponseBody
    public String joinfail(HttpSession session){
        String msg = String.valueOf(session.getAttribute("msg"));
        String body = "<div id='msg'>${msg}</div><script> alert('"+msg+"'); history.go(-1)</script>";
        session.removeAttribute("msg");
        return body;
    }
    @GetMapping("login")
    public String loginForm() {
        return "/login";
    }

    @PostMapping("login")
    public String login(LoginForm loginForm, HttpSession session) throws Exception {
        Optional<User> user = userService.login(loginForm);
        // 로그인 실패
        if (user == null) {
            return "redirect:/loginfail";
        }
        // 로그인 성공
        session.setAttribute("id", user.get().getId());
        session.setAttribute("name",user.get().getName());
        session.setAttribute("admin", user.get().getAdmin());
        return "redirect:/";
    }

    @GetMapping("logout")
    public String logout(HttpSession session) {
        session.removeAttribute("id");
        session.removeAttribute("name");
        session.removeAttribute("admin");
        return "redirect:/";
    }
    @GetMapping("loginfail")
    public String loginFail(){
        return "exception/loginfail";
    }
    @GetMapping("modifyuser")
    public String modifyuser(Model model,HttpSession session){
        User user = userService.findOnebyId((long)session.getAttribute("id")).get();
        model.addAttribute("user",userService.convertForm(user));
        return "modifyuser";
    }
    @PostMapping("modifyuser/new")
    public String modifyuserPost(UserForm userForm,@SessionAttribute("id") Long id){
        userService.modifyUser(id, userService.convertForm(userForm));
        return "redirect:/";
    }
}
