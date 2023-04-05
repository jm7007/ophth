package jm.ophthalmic.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jm.ophthalmic.domain.Reservation;
import jm.ophthalmic.domain.User;
import jm.ophthalmic.service.ReservationService;
import jm.ophthalmic.service.UserService;

@Controller
public class AdminController {

    private final UserService userService;
    private final ReservationService reservationService;
    
    public AdminController(UserService userService, ReservationService reservationService) {
        this.userService = userService;
        this.reservationService = reservationService;
    }

    @GetMapping("admin")
    public String admin(Model model){
        List<User> users = userService.findUsers();
        model.addAttribute("users", users);
        List<Reservation> reservations = reservationService.findReservations();
        model.addAttribute("reservations", reservations);
        return "admin";
    }
}
