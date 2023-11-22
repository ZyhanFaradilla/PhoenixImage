package com.phoenix.Phoenix.controller;

import com.phoenix.Phoenix.service.ReservationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/reservationLog")
public class ReservationLogController {
    @Autowired
    private ReservationLogService reservationLogService;

    @GetMapping("/index")
    public String index(@RequestParam(defaultValue = "1") Integer page,
                        @RequestParam(defaultValue = "") String roomNumber,
                        @RequestParam(defaultValue = "") String guestUsername,
                        @RequestParam(defaultValue = "") LocalDateTime bookDate, Model model){
        var rows = reservationLogService.getRowReservationLog(roomNumber, guestUsername, bookDate, page);
        model.addAttribute("grid", rows);
        model.addAttribute("totalPages", rows.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("roomNumber", roomNumber);
        model.addAttribute("guestUsername", guestUsername);
        model.addAttribute("bookDate", bookDate);
        return "reservation-log/reservation-log-index";
    }

    @GetMapping("/booking")
    public String booking(@RequestParam(required = true) String username, Model model){
        var rows = reservationLogService.getReservationBooking(username);
        model.addAttribute("grid", rows);
        return "reservation-log/reservation-booking-detail";
    }
}
