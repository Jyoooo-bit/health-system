package com.medical.healthcaresystem.entity;

import com.medical.healthcaresystem.entity.Role;
import jakarta.persistence.*;


@Entity
@Table(name = "users")   // 🔥 ADD THIS LINE
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String role;   // ADMIN, DOCTOR, PATIENT

    // getters & setters
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;



	public User() {}


    public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


	public Patient getPatient() {
		return patient;
	}


	public void setPatient(Patient patient) {
		this.patient = patient;
	}


	
}
