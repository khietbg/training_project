package com.example.trianing_project.domain;

import org.hibernate.persister.entity.Loadable;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "experience")
public class Experience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "language")
    private String language;
    @Column(name = "os")
    private String os;
    @Column(name = "framework")
    private String framework;
    @Column(name = "time_start")
    private LocalDate timeStart;
    @Column(name = "time_end")
    private LocalDate timeEnd;
    @Column(name = "team_size")
    private int teamSize;
    @Column(name = "link")
    private String link;
    @Column(name = "position")
    private String position;
    @Column(name = "work_place")
    private String workPlace;
    @Column(name = "description")
    private String description;
    @Column(name = "employee_id")
    private Long employeeId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Employee employee;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
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

    public LocalDate getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(LocalDate timeStart) {
        this.timeStart = timeStart;
    }

    public LocalDate getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(LocalDate timeEnd) {
        this.timeEnd = timeEnd;
    }

    public int getTeamSize() {
        return teamSize;
    }

    public void setTeamSize(int teamSize) {
        this.teamSize = teamSize;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getWorkPlace() {
        return workPlace;
    }

    public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace;
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

    public Experience(Long id, String language, String os, String framework, LocalDate timeStart, LocalDate timeEnd, int teamSize, String link, String position, String workPlace, String description, Long employeeId, Employee employee) {
        this.id = id;
        this.language = language;
        this.os = os;
        this.framework = framework;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.teamSize = teamSize;
        this.link = link;
        this.position = position;
        this.workPlace = workPlace;
        this.description = description;
        this.employeeId = employeeId;
        this.employee = employee;
    }

    public Experience() {
    }
}
