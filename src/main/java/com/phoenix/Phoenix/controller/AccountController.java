package com.phoenix.Phoenix.controller;

import com.phoenix.Phoenix.dto.account.ChangePasswordAdministratorDTO;
import com.phoenix.Phoenix.dto.guest.ChangePasswordGuestDTO;
import com.phoenix.Phoenix.dto.guest.RegisterGuestDTO;
import com.phoenix.Phoenix.service.GuestService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private GuestService guestService;

    @GetMapping("/registerFormGuest")
    public String registerFormGuest(Model model){
        var dto = new RegisterGuestDTO();
        model.addAttribute("dto", dto);
        return "account/register-form-guest";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("dto") RegisterGuestDTO dto, BindingResult bindingResult,
                                 Model model){
        if(bindingResult.hasErrors()){
            return "account/register-form-guest";
        }
        guestService.registerGuest(dto);
        return "redirect:/account/loginForm";
    }

    @GetMapping("/loginForm")
    public String loginForm(Model model){
        return "account/login-form";
    }

    @RequestMapping(value = "/accessDenied", method = {RequestMethod.GET, RequestMethod.POST})
    public String accessDenied(Model model){
        return "account/access-denied";
    }

    @GetMapping("/failedLogin")
    public String failedLogin(Model model){
        return "account/failed-login";
    }

    @GetMapping("/changePasswordFormAdministrator")
    public String changePasswordFormAdministrator(@RequestParam(required = true) String username, Model model){
        var dto = new ChangePasswordAdministratorDTO();
        dto.setUsername(username);
        model.addAttribute("dto",dto);
        return "account/change-password-administrator";
    }

    @PostMapping("/changePasswordAdministrator")
    public String changePasswordAdministrator(@Valid @ModelAttribute("dto") ChangePasswordAdministratorDTO dto, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "account/change-password-administrator";
        }
        guestService.changePasswordAdministrator(dto);
        return "redirect:/";
    }

    @GetMapping("/changePasswordFormGuest")
    public String changePasswordFormGuest(@RequestParam(required = true) String username, Model model){
        var dto = new ChangePasswordGuestDTO();
        dto.setUsername(username);
        model.addAttribute("dto",dto);
        return "account/change-password-guest";
    }

    @PostMapping("/changePasswordGuest")
    public String changePasswordGuest(@Valid @ModelAttribute("dto") ChangePasswordGuestDTO dto, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "account/change-password-guest";
        }
        guestService.changePasswordGuest(dto);
        return "redirect:/";
    }
}
