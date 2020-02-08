package ru.rodin.springboot.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String getLoginPage(Authentication authentication, Model model, HttpServletRequest request) {
        if (authentication != null) {
            return "redirect:/login";
        }
        if (request.getParameterMap().containsKey("error")) {
            model.addAttribute("message", "Username or password is invalid!");
        } else if(request.getParameterMap().containsKey("logout")) {
            model.addAttribute("message", "Adios, May Force be with U!");
        }
        return "login";
    }
}
