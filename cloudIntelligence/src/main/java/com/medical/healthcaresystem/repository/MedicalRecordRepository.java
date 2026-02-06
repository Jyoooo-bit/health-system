package com.medical.healthcaresystem.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.medical.healthcaresystem.dto.PatientSummaryDTO;
import com.medical.healthcaresystem.entity.MedicalRecord;
import com.medical.healthcaresystem.entity.Patient;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {

    // For patients list (only basic info)
    List<MedicalRecord> findByDoctorName(String doctorName);

    // Full history by patient id
    @Query("""
    	    SELECT new com.medical.healthcaresystem.dto.PatientSummaryDTO(
    	        p.id,
    	        p.name,
    	        m.diagnosis
    	    )
    	    FROM MedicalRecord m
    	    JOIN m.patient p
    	    WHERE m.doctorName = :doctorName
    	    """)
    	    List<PatientSummaryDTO> getPatientsForDoctor(@Param("doctorName") String doctorName);
    	




	List<MedicalRecord> findByPatient(Patient patient);

	List<MedicalRecord> findByPatientId(Long id);


}
