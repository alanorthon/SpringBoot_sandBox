package ru.rodin.springboot.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    @GetMapping("/user")
    public String getUserPage(Model model, Authentication authentication) {
        String username = ((UserDetails) authentication.getPrincipal()).getUsername();
        if (authentication.getAuthorities().toString().contains("ROLE_ADMIN")){
            model.addAttribute("hasAdminRole", true);
        }
        model.addAttribute("username", username);
        return "user";
    }
}
