package com.phoenix.Phoenix.rest;

import com.phoenix.Phoenix.service.ReservationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/reservationLog")
public class ReservationLogRestController {
    @Autowired
    private ReservationLogService reservationLogService;

    @GetMapping("/{month}/{year}")
    public ResponseEntity<Object> get(@PathVariable(required = false) Integer month,
                                      @PathVariable(required = false) Integer year){
        var dto = reservationLogService.getTotalIncome(month, year);
        return ResponseEntity.status(200).body(dto);
    }

    @GetMapping("/detail/{code}")
    public ResponseEntity<Object> getDetail(@PathVariable(required = false) String code){
        var dto = reservationLogService.detailReservation(code);
        return ResponseEntity.status(200).body(dto);
    }
}
