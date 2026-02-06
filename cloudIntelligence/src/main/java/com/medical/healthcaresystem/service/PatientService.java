package com.medical.healthcaresystem.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medical.healthcaresystem.entity.Patient;
import com.medical.healthcaresystem.repository.MedicalRecordRepository;
import com.medical.healthcaresystem.repository.PatientRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

	@Autowired
	private PatientRepository patientRepository;   // small p ✅

	@Autowired
	private MedicalRecordRepository medicalRecordRepository;


    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }
    public List<Patient> searchPatient(String name) {
        return patientRepository.findByNameContainingIgnoreCase(name);
    }

    public Patient getPatientById(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
    }

    public Patient updatePatient(Long id, Patient updated) {
        Patient patient = getPatientById(id);
        patient.setName(updated.getName());
        patient.setAge(updated.getAge());
        patient.setGender(updated.getGender());
        patient.setPhone(updated.getPhone());
        patient.setAddress(updated.getAddress());
        return patientRepository.save(patient);
    }

    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }
   

}
