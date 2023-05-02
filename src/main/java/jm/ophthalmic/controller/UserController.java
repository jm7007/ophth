package jm.ophthalmic.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import jakarta.servlet.http.HttpSession;
import jm.ophthalmic.controller.form.LoginForm;
import jm.ophthalmic.controller.form.UserForm;
import jm.ophthalmic.domain.User;
import jm.ophthalmic.service.InquiryService;
import jm.ophthalmic.service.ReservationService;
import jm.ophthalmic.service.UserService;

@Controller
public class UserController {

    private final UserService userService;
    private final ReservationService reservationService;
    private final InquiryService inquiryService;

    public UserController(UserService userService, ReservationService reservationService,
            InquiryService inquiryService) {
        this.userService = userService;
        this.reservationService = reservationService;
        this.inquiryService = inquiryService;
    }

    @GetMapping("/join")
    public String join(Model model) {
        return "join";
    }

    @PostMapping("/join/new")
    public String createAccount(UserForm userForm,HttpSession session) {
        try{
        userService.join(userService.convertForm(userForm));
        return "redirect:/";
        }catch(IllegalStateException e){
            session.setAttribute("msg", e.getMessage());
            return "redirect:/joinfail";
        }
    }
    @GetMapping("/joinfail")
    @ResponseBody
    public String joinfail(HttpSession session){
        String msg = String.valueOf(session.getAttribute("msg"));
        String body = "<div id='msg'>${msg}</div><script> alert('"+msg+"'); history.go(-1)</script>";
        session.removeAttribute("msg");
        return body;
    }
    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(LoginForm loginForm, HttpSession session, Model model) throws Exception {
        Optional<User> user = userService.login(loginForm);
        // 로그인 실패
        if (user == null) {
            return "redirect:/loginfail";
        }
        // 로그인 성공
        session.setAttribute("id", user.get().getId());
        session.setAttribute("name",user.get().getName());
        session.setAttribute("admin", user.get().getAdmin());
        if(model.getAttribute("id_save") == null){
            session.setAttribute("id_save","0");
        }else{
            session.setAttribute("id_save",user.get().getAccount());
        }
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("id");
        session.removeAttribute("name");
        session.removeAttribute("admin");
        return "redirect:/";
    }
    @GetMapping("/loginfail")
    public String loginFail(){
        return "exception/loginfail";
    }
    @GetMapping("/modifyuser")
    public String modifyuser(Model model, @SessionAttribute("id") Long id){
        User user = userService.findOnebyId(id).get();
        model.addAttribute("user",userService.convertForm(user));
        return "modifyuser";
    }
    @PostMapping("/modifyuser/new")
    public String modifyuserPost(UserForm userForm,@SessionAttribute("id") Long id, Model model){
        Optional<User> user = userService.modifyUser(id, userService.convertForm(userForm));
        if(!user.isPresent()){
            model.addAttribute("msg", "회원정보 수정이 실패하였습니다.");
            return "exception/alertandback";
        }
        return "redirect:/";
    }
    @PostMapping("/admin/modifyuser/new")
    public String adminModifyuserPost(User user, Model model){
        Optional<User> result = userService.modifyUser(user.getId(), user);
        if(!result.isPresent()){
            model.addAttribute("msg", "회원정보 수정이 실패하였습니다.");
            return "exception/alertandback";
        }
        return "redirect:/admin";
    }
    @PostMapping("/admin/user/delete")
    public String adminDeleteUser(@ModelAttribute("id") Long id, Model model){
        Optional<User> result = userService.deleteUser(id);
        if(!result.isPresent()){
            model.addAttribute("msg", id+"번 회원탈퇴가 실패하였습니다.");
            return "exception/alertandback";
        }
        return "redirect:/admin";
    }
    @PostMapping("/user/delete")
    public String deleteUser(@SessionAttribute("id") Long id, Model model){
        Optional<User> result = userService.deleteUser(id);
        if(!result.isPresent()){
            model.addAttribute("msg", "잘못된 접근입니다.");
            return "exception/alertandback";
        }
        return "redirect:/logout";
    }
    @GetMapping("/userinfo")
    public String userinfo(HttpSession session, Model model){
        if(session.getAttribute("id")==null){
            model.addAttribute("msg", "로그인 정보가 없습니다.");
            return "exception/alertandback";
        }
        Long userId = (long)session.getAttribute("id");

        model.addAttribute("user", userService.findOnebyId(userId).get());
        model.addAttribute("reservations", reservationService.findRsbyUserId(userId));
        model.addAttribute("inquiries", inquiryService.findOnebyUserId(userId));
        return "userinfo";
    }
}
