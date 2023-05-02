package jm.ophthalmic.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
import jm.ophthalmic.domain.Inquiry;
import jm.ophthalmic.domain.Reservation;
import jm.ophthalmic.domain.User;
import jm.ophthalmic.service.InquiryService;
import jm.ophthalmic.service.ReservationService;
import jm.ophthalmic.service.UserService;

@Controller
public class AdminController {

    private final UserService userService;
    private final ReservationService reservationService;
    private final InquiryService inquiryService;
    
    public AdminController(UserService userService, ReservationService reservationService,
            InquiryService inquiryService) {
        this.userService = userService;
        this.reservationService = reservationService;
        this.inquiryService = inquiryService;
    }

    @GetMapping("admin")
    public String admin(Model model,HttpSession session){
        if(session.getAttribute("admin") == null || (byte)session.getAttribute("admin") != 1){
            model.addAttribute("msg", "해킹이 감지되었습니다. ip 추적을 실행합니다.");
            return "exception/alertandback";
        }
        List<User> users = userService.findUsers();
        model.addAttribute("users", users);
        List<Reservation> reservations = reservationService.findReservations();
        model.addAttribute("reservations", reservations);
        List<Inquiry> inquiries = inquiryService.findInquiries();
        model.addAttribute("inquiries", inquiries);
        return "admin";
    }
}
