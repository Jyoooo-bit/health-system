package com.medical.healthcaresystem.controller;

import com.medical.healthcaresystem.dto.PatientSummaryDTO;
import com.medical.healthcaresystem.entity.MedicalRecord;
import com.medical.healthcaresystem.entity.User;
import com.medical.healthcaresystem.repository.MedicalFileRepository;
import com.medical.healthcaresystem.repository.MedicalRecordRepository;
import com.medical.healthcaresystem.repository.UserRepository;
import com.medical.healthcaresystem.service.LogService;
import com.medical.healthcaresystem.service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.medical.healthcaresystem.service.LogService;  // ✅ correct

import java.util.List;
@RestController
@RequestMapping("/records")
@CrossOrigin("*")
public class MedicalRecordController {
	
	@Autowired
	private LogService logService;
	@Autowired
	private MedicalRecordRepository medicalRecordRepository;



    @Autowired
    private MedicalRecordService medicalRecordService;
   

    @Autowired
    private UserRepository userRepository;   // ✅ ADDED

    // 🧑‍⚕️ DOCTOR ADDS RECORD
    @PostMapping("/medical/{patientId}")
    public MedicalRecord addRecord(@RequestBody MedicalRecord record,
                                   @PathVariable Long patientId,
                                   @RequestParam String username) {

        User user = userRepository.findByUsername(username);

        if (!user.getRole().equals("DOCTOR")) {
            throw new RuntimeException("Access Denied: Only Doctor can add records");
        }

        MedicalRecord savedRecord = medicalRecordService.saveRecord(record, patientId);

        // ✅ ADD LOG HERE (AFTER SUCCESS)
        logService.log(username, "Doctor added medical record for patient ID " + patientId);

        return savedRecord;
    }


    // 👤 VIEW RECORDS
    @GetMapping("/patient/{patientId}")
    public List<MedicalRecord> getRecords(@PathVariable Long patientId,
                                          @RequestParam String username) {

        User user = userRepository.findByUsername(username);

        if (user.getRole().equals("PATIENT") && !user.getId().equals(patientId)) {
            throw new RuntimeException("Access Denied");
        }

        List<MedicalRecord> records = medicalRecordService.getRecordsByPatient(patientId);

        // ✅ LOG HERE
        logService.log(username, "Viewed records of patient ID " + patientId);

        return records;
    }

    // 🛠 ADMIN VIEW USERS
    @GetMapping("/users")
    public List<User> getAllUsers(@RequestParam String username) {

        User user = userRepository.findByUsername(username);

        if (!user.getRole().equals("ADMIN")) {
            throw new RuntimeException("Only Admin allowed");
        }

        List<User> users = userRepository.findAll();

        // ✅ LOG HERE
        logService.log(username, "Admin viewed all users");

        return users;
    }
    
    @GetMapping("/dashboard/doctor")
    public List<MedicalRecord> doctorDashboard(@RequestParam String doctorName,
                                               @RequestParam String username) {

        User user = userRepository.findByUsername(username);

        if (!user.getRole().equals("DOCTOR")) {
            throw new RuntimeException("Access Denied");
        }
        return medicalRecordRepository.findByDoctorName(doctorName); 
    }
    @PutMapping("/update/{recordId}")
    public MedicalRecord updateRecord(@PathVariable Long recordId,
                                      @RequestBody MedicalRecord updated,
                                      @RequestParam String username) {

        User user = userRepository.findByUsername(username);

        if (!user.getRole().equals("DOCTOR")) {
            throw new RuntimeException("Only Doctor can update records");
        }

        MedicalRecord record = medicalRecordService.getRecordById(recordId);

        record.setDiagnosis(updated.getDiagnosis());
        record.setPrescription(updated.getPrescription());
        record.setTreatment(updated.getTreatment());
        record.setVisitDate(updated.getVisitDate());

        logService.log(username, "Updated medical record ID " + recordId);

        return medicalRecordService.save(record);
    }
    @GetMapping("/doctor/patients")
    public List<PatientSummaryDTO> getPatients(@RequestParam String doctorName) {
        return medicalRecordService.getPatientsForDoctor(doctorName);
    }

    @GetMapping("/doctor/patient/{id}")
    public List<MedicalRecord> getPatientHistory(@PathVariable Long id) {
        return medicalRecordService.getRecordsByPatient(id);
    }



}
