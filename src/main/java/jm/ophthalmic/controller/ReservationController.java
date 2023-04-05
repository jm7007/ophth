package jm.ophthalmic.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.persistence.EntityManager;
import jm.ophthalmic.domain.Reservation;
import jm.ophthalmic.service.ReservationService;

@Controller
public class ReservationController {

    private final ReservationService reservationService;
    
    public ReservationController(ReservationService reservationService, EntityManager em) {
        this.reservationService = reservationService;
    }

    @GetMapping("rs")
    public String reservation(Model model){
        List<Reservation> reservations = reservationService.findReservations();
        model.addAttribute("reservations", reservations);
        return "reservation";
    }
    @PostMapping("rs/new")
    public String createAccount(ReservationForm rsForm){
        Reservation rs = new Reservation();
        rs.setRs_name(rsForm.getRs_name());
        rs.setRs_contact(rsForm.getRs_contact());
        rs.setRs_datetime(rsForm.getRs_datetime());
        rs.setRs_info(rsForm.getRs_info());
        reservationService.reservate(rs);
        System.out.println("new Reservation : "+rs);
        return "redirect:/";
    }
}
