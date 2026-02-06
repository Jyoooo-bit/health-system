package com.medical.healthcaresystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.medical.healthcaresystem.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // For login
    Optional<User> findByUsernameAndPassword(String username, String password);

    // To check if user already exists
     public User findByUsername(String username);

}
