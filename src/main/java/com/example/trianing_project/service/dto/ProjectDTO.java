package com.example.trianing_project.service.dto;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

public class ProjectDTO implements Serializable {
    private Long id;
    @NotEmpty(message = "Name is required")
    @Size(min = 5, message = "Project name must have at least 1 character")
    private String name;

    private String os;

    private String framework;

    private String language;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Start date is required")
    private LocalDate startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Start date is required")
    private LocalDate endDate;
    private Long pmId;
    private String pmName;


    public ProjectDTO() {
    }

    public ProjectDTO(Long id, String name, String os, String framework, String language, LocalDate startDate, LocalDate endDate, Long pmId, String pmName) {
        this.id = id;
        this.name = name;
        this.os = os;
        this.framework = framework;
        this.language = language;
        this.startDate = startDate;
        this.endDate = endDate;
        this.pmId = pmId;
        this.pmName = pmName;
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

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getFramework() {
        return framework;
    }

    public void setFramework(String framework) {
        this.framework = framework;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Long getPmId() {
        return pmId;
    }

    public void setPmId(Long pmId) {
        this.pmId = pmId;
    }

    public String getPmName() {
        return pmName;
    }

    public void setPmName(String pmName) {
        this.pmName = pmName;
    }
}