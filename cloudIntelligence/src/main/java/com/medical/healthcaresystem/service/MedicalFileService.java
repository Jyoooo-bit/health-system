package com.medical.healthcaresystem.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.medical.healthcaresystem.entity.MedicalFile;
import com.medical.healthcaresystem.entity.MedicalRecord;
import com.medical.healthcaresystem.repository.MedicalFileRepository;
import com.medical.healthcaresystem.repository.MedicalRecordRepository;

@Service
public class MedicalFileService {

    @Autowired
    private MedicalFileRepository fileRepository;

    @Autowired
    private MedicalRecordRepository recordRepository;

    private final String UPLOAD_DIR = "uploads/";

    public MedicalFile uploadFile(Long recordId, MultipartFile file) throws IOException {

        MedicalRecord record = recordRepository.findById(recordId)
                .orElseThrow(() -> new RuntimeException("Record not found"));

        File dir = new File(UPLOAD_DIR);
        if (!dir.exists()) dir.mkdirs();

        String filePath = UPLOAD_DIR + file.getOriginalFilename();
        file.transferTo(new File(filePath));

        MedicalFile medicalFile = new MedicalFile();
        medicalFile.setFileName(file.getOriginalFilename());
        medicalFile.setFileType(file.getContentType());
        medicalFile.setFilePath(filePath);
        medicalFile.setMedicalRecord(record);

        return fileRepository.save(medicalFile);
    }

    public List<MedicalFile> getFilesByRecord(Long recordId) {

        MedicalRecord record = recordRepository.findById(recordId)
                .orElseThrow(() -> new RuntimeException("Record not found"));

        return fileRepository.findByMedicalRecord(record);
    }

	public static List<MedicalRecord> getRecordsByPatient(Long patientId) {
		// TODO Auto-generated method stub
		return null;
	}

}
