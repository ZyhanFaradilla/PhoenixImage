package com.phoenix.Phoenix.controller;

import com.phoenix.Phoenix.service.AdministratorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/administrators")
public class AdministratorsController {
    @Autowired
    private AdministratorsService administratorsService;

    @GetMapping("/index")
    public String index(@RequestParam(defaultValue = "1") Integer page, Model model){
        var rows = administratorsService.getRowAdmin(page);
        model.addAttribute("grid", rows);
        model.addAttribute("totalPages", rows.getTotalPages());
        model.addAttribute("currentPage", page);
        return "administrators/admin";
    }
}
