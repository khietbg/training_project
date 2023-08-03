package com.example.trianing_project.service.dto;


import java.io.Serializable;

public class SkillDTO implements Serializable {
    private Long id;
    private String name;
    private int level;
    private String description;
    private int month;
    private int year;
    private Long employeeId;
    private String employeeName;

    public SkillDTO() {
    }

    public SkillDTO(Long id, String name, int level, String description, int month, int year, Long employeeId, String employeeName) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.description = description;
        this.month = month;
        this.year = year;
        this.employeeId = employeeId;
        this.employeeName = employeeName;
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
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
