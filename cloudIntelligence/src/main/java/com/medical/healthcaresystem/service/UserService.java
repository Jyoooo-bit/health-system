package com.medical.healthcaresystem.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medical.healthcaresystem.entity.Patient;
import com.medical.healthcaresystem.entity.User;
import com.medical.healthcaresystem.repository.PatientRepository;
import com.medical.healthcaresystem.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PatientRepository patientRepository;


    // 🔐 LOGIN
    public String login(String username, String password) {

        User user = userRepository.findByUsername(username);

        if (user == null) return "User not found";
        if (!user.getPassword().equals(password)) 
        	return "Invalid password";

        return "Login successful as " + user.getRole();
    }

    // 📝 REGISTER USER
    public User register(User user) {
        return userRepository.save(user);   // ✅ FIXED
    }

    // 🔍 Check user exists
    public Optional<User> findByUsername(String username) {
        return Optional.ofNullable(userRepository.findByUsername(username));
    }
    public User saveUser(User user) {
        return userRepository.save(user);
    }
    
           public User registerUser(User user) {

            if (user.getRole().equals("PATIENT")) {

                Patient patient = new Patient();
                patient.setName(user.getUsername());  // temporary name
                patient = patientRepository.save(patient);

                user.setPatient(patient);   // 🔗 LINKING
            }

            return userRepository.save(user);
        }
    }



	

