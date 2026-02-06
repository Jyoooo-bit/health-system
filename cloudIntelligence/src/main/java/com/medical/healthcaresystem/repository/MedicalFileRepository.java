package com.medical.healthcaresystem.repository;

import com.medical.healthcaresystem.entity.MedicalFile;
import com.medical.healthcaresystem.entity.MedicalRecord;
import com.medical.healthcaresystem.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalFileRepository extends JpaRepository<MedicalFile, Long> {

    List<MedicalFile> findByMedicalRecord(MedicalRecord record);

    List<MedicalFile> findByMedicalRecord_Patient(Patient patient);

}
