package com.example.trianing_project.service.dto;

import javax.validation.constraints.Size;

public class DepartmentDTO {
    private Long id;
    @Size(min = 5,max = 50,message = "name for 5 to 50 char")
    private String name;
    @Size(min = 3,message ="Department code for 3 char")
    private  String departmentCode;
    private String description;
    private Long parentId;
    private String parentName;

    public DepartmentDTO() {
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

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }
}
