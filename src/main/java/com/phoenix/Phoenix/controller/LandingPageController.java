package com.phoenix.Phoenix.controller;

import com.phoenix.Phoenix.dto.reservationLog.AnnualIncomeDTO;
import com.phoenix.Phoenix.dto.utility.DropdownDTO;
import com.phoenix.Phoenix.service.LandingAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
@RequestMapping("/landing")
public class LandingPageController {
    @Autowired
    private LandingAdminService landingAdminService;

    @GetMapping("/page")
    public String home(){
        return "landing/page";
    }

    @GetMapping("/pageAdmin")
    public String homeAdmin(Model model){
        var orderYears = landingAdminService.getReservationYears();
        var yearDropdown = new ArrayList<DropdownDTO>();
        for(var orderYear : orderYears){
            yearDropdown.add(new DropdownDTO(orderYear, orderYear.toString()));
        }
        model.addAttribute("yearDropdown", yearDropdown);
        return "landing/page-admin";
    }

    @GetMapping("/annualIncome/{year}")
    public ResponseEntity<Object> annualIncome(@PathVariable(required = true) Integer year){
        var data = landingAdminService.getAnnualIncome(year);
        var totalIncome = landingAdminService.getTotalIncome(data);
        var fluctuation = landingAdminService.getFluctuation(data, year);
        var highestPeriod = landingAdminService.getHighestPeriod(data);
        var lowestPeriod = landingAdminService.getLowestPeriod(data);
        var dto = new AnnualIncomeDTO(data, year, totalIncome, fluctuation, highestPeriod, lowestPeriod);
        return ResponseEntity.status(200).body(dto);
    }
}
