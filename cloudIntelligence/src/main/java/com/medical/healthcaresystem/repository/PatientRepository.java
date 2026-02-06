package com.medical.healthcaresystem.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medical.healthcaresystem.entity.MedicalRecord;
import com.medical.healthcaresystem.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
	List<Patient> findByNameContainingIgnoreCase(String name);


}
