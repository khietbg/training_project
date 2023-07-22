package com.example.trianing_project.domain;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "certificate")
public class Certificate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "issue_date")
    private LocalDate issueDate;
    @Column(name = "expiration_date")
    private LocalDate expirationDate;
    @Column(name = "cerrtificate_officer")
    private String certificateOfficer;
    @Column(name = "description")
    private String description;
    @Column(name = "employee_id")
    private Long employeeId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Employee employee;

    public Certificate() {
    }

    public Certificate(Long id, String name, LocalDate issueDate, LocalDate expirationDate, String certificateOfficer, String description, Long employeeId, Employee employee) {
        this.id = id;
        this.name = name;
        this.issueDate = issueDate;
        this.expirationDate = expirationDate;
        this.certificateOfficer = certificateOfficer;
        this.description = description;
        this.employeeId = employeeId;
        this.employee = employee;
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

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
