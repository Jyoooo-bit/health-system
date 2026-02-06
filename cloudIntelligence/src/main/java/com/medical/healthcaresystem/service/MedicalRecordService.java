package com.medical.healthcaresystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medical.healthcaresystem.dto.PatientSummaryDTO;
import com.medical.healthcaresystem.entity.MedicalRecord;
import com.medical.healthcaresystem.entity.Patient;
import com.medical.healthcaresystem.repository.MedicalRecordRepository;
import com.medical.healthcaresystem.repository.PatientRepository;
import com.medical.healthcaresystem.repository.UserRepository;

@Service
public class MedicalRecordService {

    @Autowired
    private MedicalRecordRepository medicalRepo;

    @Autowired
    private PatientRepository patientRepo;

    // 🔹 Save record for patient
    public MedicalRecord saveRecord(MedicalRecord record, Long patientId) {

        Patient patient = patientRepo.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        record.setPatient(patient);

        return medicalRepo.save(record);
    }

    // 🔹 Get record by ID
    public MedicalRecord getRecordById(Long id) {
        return medicalRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Record not found"));
    }

    public List<MedicalRecord> getRecordsByPatient(Long patientId) {

        Patient patient = patientRepo.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        return medicalRepo.findByPatient(patient); 
    }


    // 🔹 Update record
    public MedicalRecord save(MedicalRecord record) {
        return medicalRepo.save(record);
    }
    public List<PatientSummaryDTO> getPatientsForDoctor(String doctorName) {
        return medicalRepo.getPatientsForDoctor(doctorName);
    }

   

}

	


    
	
	

