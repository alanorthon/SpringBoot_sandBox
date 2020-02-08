package ru.rodin.springboot.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.rodin.springboot.model.User;
import ru.rodin.springboot.service.UserService;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public AdminController(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @GetMapping(value = "/userlist")
    public String getAllUsers(Model model) {
        model.addAttribute("userList", userService.allUsers());
        return "admin";
    }

    @PostMapping(value = "/editUser")
    public String updateUser(@RequestParam("id") String id,
                             @RequestParam String password,
                             @RequestParam String username,
                             @RequestParam String newPassword,
                             @RequestParam String email,
                             @RequestParam String role,
                             Model model) {
        Long tempId = Long.parseLong(id);
        User user;
        if (password.equals(bCryptPasswordEncoder.encode(newPassword))) {
            user = new User(tempId, username, email);
        } else {
            user = new User(tempId, username, newPassword, email);
        }
        userService.setRoleByName(user, role);
        userService.updateUser(user);
        model.addAttribute("message", "User successfully updated!");
        model.addAttribute("userList", userService.allUsers());
        return "admin";
    }

    @PostMapping(value = "/addUser")
    public String addUser(@RequestParam String password,
                          @RequestParam String username,
                          @RequestParam String email,
                          @RequestParam String role,
                          Model model) {
        User user = new User(username, password, email);
        if (userService.addUser(user, role)) {
            model.addAttribute("message", "User successfully added!");
        } else {
            model.addAttribute("message", "User with this username is already registered!");
        }
        model.addAttribute("userList", userService.allUsers());
        return "admin";
    }

    @GetMapping(value = "/deleteUser/{id}")
    public String deleteUser(@PathVariable Long id, Model model) {
        model.addAttribute("message", "User successfully deleted!");
        userService.deleteUserById(id);
        model.addAttribute("userList", userService.allUsers());
        return "admin";
    }
}
