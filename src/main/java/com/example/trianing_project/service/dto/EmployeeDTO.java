package com.example.trianing_project.service.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Set;

public class EmployeeDTO {
   private Long id;
   private String avatarUrl;
   private String firstName;
   private String lastName;
   @Email(message = "email in valid")
   private String email;
   @Size(min = 6)
   private String password;
   @Pattern(regexp = "0\\\\d{9}")
   private String phone;
   private String address;
   private String employeeCode;
   private LocalDate birthDate;
   private String citizenCode;
   private LocalDate licenseDate;
   private LocalDate licensePlace;
   private LocalDate startDate;
   private float coefficientsSalary;
   private String position;
   private String departmentName;
   private Long departmentId;
   private String managerName;
   private Long managerId;
   private boolean sex;
   private int level;
   private Set<String> roles;
   private Set<String> projects;

   public Long getId() {
      return id;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getAvatarUrl() {
      return avatarUrl;
   }

   public void setAvatarUrl(String avatarUrl) {
      this.avatarUrl = avatarUrl;
   }

   public String getFirstName() {
      return firstName;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public String getLastName() {
      return lastName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getPhone() {
      return phone;
   }

   public void setPhone(String phone) {
      this.phone = phone;
   }

   public String getAddress() {
      return address;
   }

   public void setAddress(String address) {
      this.address = address;
   }

   public String getEmployeeCode() {
      return employeeCode;
   }

   public void setEmployeeCode(String employeeCode) {
      this.employeeCode = employeeCode;
   }

   public LocalDate getBirthDate() {
      return birthDate;
   }

   public void setBirthDate(LocalDate birthDate) {
      this.birthDate = birthDate;
   }

   public String getCitizenCode() {
      return citizenCode;
   }

   public void setCitizenCode(String citizenCode) {
      this.citizenCode = citizenCode;
   }

   public LocalDate getLicenseDate() {
      return licenseDate;
   }

   public void setLicenseDate(LocalDate licenseDate) {
      this.licenseDate = licenseDate;
   }

   public LocalDate getLicensePlace() {
      return licensePlace;
   }

   public void setLicensePlace(LocalDate licensePlace) {
      this.licensePlace = licensePlace;
   }

   public LocalDate getStartDate() {
      return startDate;
   }

   public void setStartDate(LocalDate startDate) {
      this.startDate = startDate;
   }

   public float getCoefficientsSalary() {
      return coefficientsSalary;
   }

   public void setCoefficientsSalary(float coefficientsSalary) {
      this.coefficientsSalary = coefficientsSalary;
   }

   public String getPosition() {
      return position;
   }

   public void setPosition(String position) {
      this.position = position;
   }

   public String getDepartmentName() {
      return departmentName;
   }

   public void setDepartmentName(String departmentName) {
      this.departmentName = departmentName;
   }

   public Long getDepartmentId() {
      return departmentId;
   }

   public void setDepartmentId(Long departmentId) {
      this.departmentId = departmentId;
   }

   public String getManagerName() {
      return managerName;
   }

   public void setManagerName(String managerName) {
      this.managerName = managerName;
   }

   public Long getManagerId() {
      return managerId;
   }

   public void setManagerId(Long managerId) {
      this.managerId = managerId;
   }

   public boolean isSex() {
      return sex;
   }

   public void setSex(boolean sex) {
      this.sex = sex;
   }

   public int getLevel() {
      return level;
   }

   public void setLevel(int level) {
      this.level = level;
   }

   public Set<String> getRoles() {
      return roles;
   }

   public void setRoles(Set<String> roles) {
      this.roles = roles;
   }

   public Set<String> getProjects() {
      return projects;
   }

   public void setProjects(Set<String> projects) {
      this.projects = projects;
   }
}
