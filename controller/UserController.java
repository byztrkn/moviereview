package com.Softito.MovieCatalog.controller;

import com.Softito.MovieCatalog.model.Movie;
import com.Softito.MovieCatalog.model.User;
import com.Softito.MovieCatalog.repository.UserRepository;
import com.Softito.MovieCatalog.request.LoginRequest;
import com.Softito.MovieCatalog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }



    @GetMapping("/addUser")
    public String showAddUser(Model model) {
        model.addAttribute("user", new User());
        return "addUser";
    }

    @PostMapping("/addUser")
    public String addUser(@ModelAttribute("user") User user, Model model) {
        String email = user.getEmail();

        if (userService.emailExists(email)) {
            model.addAttribute("errorMessage", "This email is already being used");
            return "addUser";
        }
        model.addAttribute("successMessage", "User added successfully");

        userService.saveUser(user);
        return "addUser";
    }



    @GetMapping("/listAllUsers")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "listAllUsers";
    }



    @GetMapping("/updateUser/{id}")
    public String showUpdateUserById(@PathVariable("id") Long id, Model model) {

        model.addAttribute("user", userService.getUserById(id));

        return "updateUser";
    }

    @PostMapping("/updateUser/{id}")
    public String updateUserById(@PathVariable("id") Long id, @ModelAttribute("user") User updatedUser, Model model) {

        model.addAttribute("user", updatedUser);

        userService.updateUser(id, updatedUser);
        return "redirect:/listAllUsers";
    }



    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/listAllUsers";
    }

}
