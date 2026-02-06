package com.medical.healthcaresystem.entity;

import jakarta.persistence.*;

@Entity
public class MedicalFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;
    private String fileType;
    private String filePath;
    private String doctorName;
	// 🔥 THIS IS MISSING IN YOUR PROJECT
    @ManyToOne
    @JoinColumn(name = "record_id")
    private MedicalRecord medicalRecord;

    // getters & setters


    public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public void setId(Long id) {
		this.id = id;
	}





    public Long getId() { return id; }

    public String getFileName() { return fileName; }
    public void setFileName(String fileName) { this.fileName = fileName; }

    public String getFileType() { return fileType; }
    public void setFileType(String fileType) { this.fileType = fileType; }

    public String getFilePath() { return filePath; }
    public void setFilePath(String filePath) { this.filePath = filePath; }

    public MedicalRecord getMedicalRecord() { return getMedicalRecord(); }
    public void setMedicalRecord(MedicalRecord medicalRecord) { this.medicalRecord = medicalRecord; }
 }

