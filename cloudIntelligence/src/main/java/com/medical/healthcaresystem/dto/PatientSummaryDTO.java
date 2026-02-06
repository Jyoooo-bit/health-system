package com.medical.healthcaresystem.dto;

public class PatientSummaryDTO {
    private Long id;
    private String name;
    private String disease;

    public PatientSummaryDTO(Long id, String name, String disease) {
        this.id = id;
        this.name = name;
        this.disease = disease;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getDisease() { return disease; }
}
