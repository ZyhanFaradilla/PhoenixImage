package com.phoenix.Phoenix.controller;

import com.phoenix.Phoenix.service.InventoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/inventories")
public class InventoriesController {
    @Autowired
    private InventoriesService inventoriesService;

    @GetMapping("/index")
    public String index(@RequestParam(defaultValue = "1") Integer page, Model model){
        var rows = inventoriesService.getRowInventories(page);
        model.addAttribute("grid", rows);
        model.addAttribute("totalPages", rows.getTotalPages());
        model.addAttribute("currentPage", page);
        return "inventories/inventories-index";
    }
}
