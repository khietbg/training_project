package com.example.trianing_project.service.dto;

import java.time.LocalDate;

public class CertificateDTO {
    private Long id;
    private String name;
    private LocalDate issueDate;
    private LocalDate expirationDate;
    private String certificateOfficer;
    private String description;
    private Long employeeId;
    private String employeeName;

    public CertificateDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCertificateOfficer() {
        return certificateOfficer;
    }

    public void setCertificateOfficer(String certificateOfficer) {
        this.certificateOfficer = certificateOfficer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
}
