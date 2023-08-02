package com.example.trianing_project.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "avatar_url")
    private String avatarUrl;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    @Email(message = "email in valid!")
    private String email;
    @Column(name = "address")
    private String address;
    @Column(name = "phone")
    private String phone;
    @Size(min = 6)
    @Column(name = "password")
    private String password;
    @Column(name = "sex")
    private boolean sex;
    @Column(name = "date_of_birth")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthDate;
    @Column(name = "citizen_code")
    private String citizenCode;
    @Column(name = "license_date")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate licenseDate;
    @Column(name = "licensePlace")
    private String licensePlace;
    @Column(name = "employee_code")
    private String employeeCode;
    @Column(name = "start_date")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate startDate;
    @Column(name = "coefficients_salary")
    private float coefficientsSalary;
    @Column(name = "position")
    private String position;
    @Column(name = "level")
    private int level;
    @Column(name = "manager_id")
    private Long managerId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Employee manager;
    @Column(name = "department_id")
    private Long departmentId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Department department;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "employee_role",
            joinColumns = {@JoinColumn(name = "employee_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_name")})
    private Set<Role> roles = new HashSet<>();
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "employee_project",
            joinColumns = {@JoinColumn(name = "employee_id")},
            inverseJoinColumns = {@JoinColumn(name = "project_id")})
    private Set<Project> projects = new HashSet<>();
    @Column(name = "status")
    private boolean status;

    public Long getId() {
        return id;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
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

    public String getLicensePlace() {
        return licensePlace;
    }

    public void setLicensePlace(String licensePlace) {
        this.licensePlace = licensePlace;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
