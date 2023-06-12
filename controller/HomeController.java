package com.Softito.MovieCatalog.controller;

import com.Softito.MovieCatalog.model.User;
import com.Softito.MovieCatalog.request.LoginRequest;
import com.Softito.MovieCatalog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {
    private UserService userService;

    @Autowired
    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String submitRegistrationForm(@ModelAttribute("user") User user, Model model) {
        String email = user.getEmail();
        if (userService.emailExists(email)) {
            model.addAttribute("errorMessage", "This email is already being used");
            return "register";
        }
        model.addAttribute("user", user);
        userService.saveUser(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginPage(Model model) {
        model.addAttribute("user", new LoginRequest());
        return "login";
    }

    @PostMapping("/login")
    public String submitLoginPage(@ModelAttribute("loginRequest") LoginRequest loginRequest, Model model) {
        User correctUser = userService.getUserByEmail(loginRequest.getEmail());
        if (loginRequest.getPassword().equals(correctUser.getPassword())) {
            return "redirect:/home";
        } else {
            model.addAttribute("errorMessage", "Password is incorrect");
            return "login";
        }
    }

    @GetMapping("/home")
    public String userHome() {
        return "home";
    }
}
