package com.medical.healthcaresystem.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.medical.healthcaresystem.entity.MedicalRecord;
import com.medical.healthcaresystem.entity.Patient;
import com.medical.healthcaresystem.entity.User;
import com.medical.healthcaresystem.repository.UserRepository;
import com.medical.healthcaresystem.service.MedicalFileService;
import com.medical.healthcaresystem.service.MedicalRecordService;
import com.medical.healthcaresystem.service.PatientService;

import java.util.List;

@RestController
@RequestMapping("/patients")
@CrossOrigin("*")
public class PatientController {
	@Autowired
	private MedicalRecordService medicalRecordService;

	@Autowired
	private UserRepository userRepository;

	

    @Autowired
    private PatientService patientService;

    
    
    
    // ➕ Add new patient
    @PostMapping("/add")
    public Patient addPatient(@RequestBody Patient patient) {
        return patientService.savePatient(patient);
    }

    // 📋 Get all patients
    @GetMapping("/all")
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    // 🔍 Search by name
    @GetMapping("/search")
    public List<Patient> searchPatient(@RequestParam String name) {
        return patientService.searchPatient(name);
    }

    // 🆔 Get patient by ID (YOU NEED THIS)
    @GetMapping("/{id}")
    public Patient getPatient(@PathVariable Long id) {
        return patientService.getPatientById(id);
    }

    // ✏ Update patient
    @PutMapping("/update/{id}")
    public Patient updatePatient(@PathVariable Long id, @RequestBody Patient patient) {
        return patientService.updatePatient(id, patient);
    }

    // ❌ Delete patient
    @DeleteMapping("/delete/{id}")
    public String deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return "Patient deleted successfully";
    }
    @GetMapping("/dashboard/patient/{patientId}")
    public List<MedicalRecord> patientDashboard(@PathVariable Long patientId,
                                                @RequestParam String username) {

        User user = userRepository.findByUsername(username);

        if (!user.getRole().equals("PATIENT")) {
            throw new RuntimeException("Access Denied");
        }

        if (user.getPatient() == null || !user.getPatient().getId().equals(patientId)) {
            throw new RuntimeException("You can view only your records");
        }

        return medicalRecordService.getRecordsByPatient(patientId);  // ✅ correct
    }


}
