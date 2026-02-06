package com.medical.healthcaresystem.controller;

import com.medical.healthcaresystem.entity.Log;
import com.medical.healthcaresystem.entity.User;
import com.medical.healthcaresystem.repository.LogRepository;
import com.medical.healthcaresystem.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/logs")
public class LogController {

    @Autowired
    private LogRepository logRepository;

    @GetMapping("/all")
    public List<Log> getLogs(@RequestParam String username,
                             @Autowired UserRepository userRepository) {

        User user = userRepository.findByUsername(username);

        if (!user.getRole().equals("ADMIN")) {
            throw new RuntimeException("Only admin can view logs");
        }

        return logRepository.findAll();
    }
}
