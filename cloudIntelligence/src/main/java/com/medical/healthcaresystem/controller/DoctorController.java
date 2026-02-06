package com.medical.healthcaresystem.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.medical.healthcaresystem.entity.MedicalRecord;
import com.medical.healthcaresystem.repository.MedicalRecordRepository;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private MedicalRecordRepository recordRepo;

    // 🟢 List of patients under doctor
    @GetMapping("/patients")
    public List<Map<String,Object>> getPatients(@RequestParam String doctorName){

        List<MedicalRecord> records = recordRepo.findByDoctorName(doctorName);

        return records.stream().map(r -> {
            Map<String,Object> m = new HashMap<>();
            m.put("id", r.getPatient().getId());
            m.put("name", r.getPatient().getName());
            m.put("disease", r.getDiagnosis());
            return m;
        }).toList();
    }

    // 🟢 Full history of one patient
    @GetMapping("/patient/{id}")
    public List<MedicalRecord> getHistory(@PathVariable Long id){
      return recordRepo.findByPatientId(id);
    }
}
