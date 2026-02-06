package com.medical.healthcaresystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.medical.healthcaresystem.entity.Role;
import com.medical.healthcaresystem.entity.User;
import com.medical.healthcaresystem.repository.UserRepository;
import com.medical.healthcaresystem.service.UserService;

import java.util.Optional;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // ✅ REGISTER
    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userRepository.save(user);
    }

    // ✅ LOGIN
    @PostMapping("/login")
    public String login(@RequestBody User user) {
        User dbUser = userRepository.findByUsername(user.getUsername());

        if (dbUser == null) return "User not found";

        if (!dbUser.getPassword().equals(user.getPassword()))
            return "Invalid password";

        return "Login Success - " + dbUser.getRole();
    }
}
