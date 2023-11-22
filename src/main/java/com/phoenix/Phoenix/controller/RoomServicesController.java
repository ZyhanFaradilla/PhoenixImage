package com.phoenix.Phoenix.controller;

import com.phoenix.Phoenix.dto.roomServices.InsertRosterRoomServicesDTO;
import com.phoenix.Phoenix.service.RoomServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/roomServices")
public class RoomServicesController {
    @Autowired
    private RoomServicesService roomServicesService;

    @GetMapping("/index")
    public String index(@RequestParam(defaultValue = "1") Integer page,
                        @RequestParam(defaultValue = "") String employeeNumber,
                        @RequestParam(defaultValue = "") String fullName, Model model){
        var rows = roomServicesService.getRowRoomServices(employeeNumber, fullName, page);
        model.addAttribute("grid", rows);
        model.addAttribute("totalPages", rows.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("employeeNumber", employeeNumber);
        model.addAttribute("fullName", fullName);
        return "room-services/room-services-index";
    }

    @GetMapping("/roster")
    public String roster(@RequestParam(required = true) String employeeNumber, Model model){
        var rows = roomServicesService.getRoster(employeeNumber);
        var header = roomServicesService.getHeaderRoomServices(employeeNumber);
        model.addAttribute("grid", rows);
        model.addAttribute("header", header);
        return "room-services/room-services-roster";
    }

    @GetMapping("/rosterUpsert")
    public String rosterUpsert(@RequestParam(required = true) String employeeNumber, Model model){
        var dto = new InsertRosterRoomServicesDTO();
        model.addAttribute("dto", dto);
        dto.setEmployeeNumber(employeeNumber);
        return "room-services/room-services-roster-form";
    }

    @PostMapping("/rosterInsert")
    public String rosterInsert(@ModelAttribute("dto") InsertRosterRoomServicesDTO dto, RedirectAttributes redirectAttributes){
        roomServicesService.insertRoster(dto.getEmployeeNumber(), dto);
        redirectAttributes.addAttribute("employeeNumber", dto.getEmployeeNumber());
        return "redirect:/roomServices/roster";
    }
}
