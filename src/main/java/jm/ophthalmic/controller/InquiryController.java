package jm.ophthalmic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
import jm.ophthalmic.domain.Inquiry;
import jm.ophthalmic.service.InquiryService;
import jm.ophthalmic.service.UserService;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class InquiryController {

    private final InquiryService inquiryService;
    private final UserService userService;
    
    public InquiryController(InquiryService inquiryService, UserService userService) {
        this.inquiryService = inquiryService;
        this.userService = userService;
    }
    //로그인 세션이 존재할 경우 바로 입력창으로 넘어간다
    @GetMapping("inquiry")
    public String inquiry(HttpSession session){
        return (session.getAttribute("id") == null) ? "/inquiry/iq-enter" : "redirect:/inquiry/iq-register";
    }
    //비회원으로 문의를 요청하는 경우 이름과 연락처를 먼저 입력하면 모델에 데이터를 담아 입력창으로 넘어간다
    @PostMapping("inquiry/iq-enter")
    public String nonUserInquiry(Inquiry inquiry, HttpSession session) {
        session.setAttribute("iqname", inquiry.getInquiry_name());
        session.setAttribute("iqcontact", inquiry.getInquiry_contact());
        return "redirect:/iq-register";
    }
    @GetMapping("inquiry/iq-register")
    public String InquiryRegister(Model model, HttpSession session){
        //로그인 세션이 있을 경우 유저의 연락처 호출
        if(session.getAttribute("id") != null){
            model.addAttribute("iqname", session.getAttribute("name"));
            model.addAttribute("iqcontact", userService.findOnebyId((long)session.getAttribute("id")).get().getContact());
            return "inquiry/iq-register";
        //비회원 문의 세션이 있을 경우 데이터를 모델에 옮기고 세션 삭제
        }else if(session.getAttribute("iqname") != null){
            model.addAttribute("iqname",session.getAttribute("iqname"));
            model.addAttribute("iqcontact",session.getAttribute("iqcontact"));
            session.removeAttribute("iqname");
            session.removeAttribute("iqcontact");
            return "inquiry/iq-register";
        }else{
            return "exception/wrong";
        }
    }
    @PostMapping("inquiry/iq-register")
    public String InquiryRegisterPost(Inquiry inquiry, HttpSession session){
        if(inquiry == null){
            return "exception/wrong";
        }
        //로그인id 세션이 있을 경우 user_id 필드에 대입
        if(session.getAttribute("id") != null){
            inquiry.setUser_id((long)session.getAttribute("id"));
        }
        //이미지 파일이 있을 경우 이미지 정보 디테일을 저장하는 메서드 호출
        if(inquiry.getInquiry_image() != null){
            inquiry = inquiryService.saveImageDetail(inquiry);
        }
        inquiryService.register(inquiry);
        return "redirect:/";
    }
    
}
