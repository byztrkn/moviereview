package com.Softito.MovieCatalog.service;

import com.Softito.MovieCatalog.enums.Role;
import com.Softito.MovieCatalog.model.Actor;
import com.Softito.MovieCatalog.model.User;
import com.Softito.MovieCatalog.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



    public boolean emailExists(String email) {
        User user = userRepository.findByEmail(email);
        return user != null;
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }



    public User saveUser(User user) {

        user.setRole(Role.USER);
        return userRepository.save(user);
    }



    public List<User> getAllUsers() {
        return userRepository.findAll();
    }



    public User getUserById(Long id) {
        return userRepository.getById(id);
    }

    public User updateUser(Long id, User updatedUser) {

        User user = getUserById(id);

        user.setFirstName(updatedUser.getFirstName());
        user.setLastName(updatedUser.getLastName());
        user.setEmail(updatedUser.getEmail());
        user.setPassword(updatedUser.getPassword());

        return userRepository.save(user);
    }



    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}

