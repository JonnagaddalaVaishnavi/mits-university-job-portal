package mits.miniproject.universityjobportal.Entity;

import jakarta.persistence.*;
import mits.miniproject.universityjobportal.Utility.Status;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class JobEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "coordinator_id")
    private CoordinatorEntity coordinator;   // ← name wasn't updated when you changed the type
    private String title;
    private String companyName;
    private String description;
    private String location;
    private BigDecimal salary;
    private LocalDateTime applicationStartDate;
    private LocalDateTime applicationEndDate;

    @Enumerated(EnumType.STRING)
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate(){
        this.createdAt=LocalDateTime.now();
        this.updatedAt=LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate(){
        this.updatedAt=LocalDateTime.now();
    }

    public JobEntity() {
    }

    public JobEntity(Long id, CoordinatorEntity coordinatorId, String title, String companyName, String description, String location, BigDecimal salary, LocalDateTime applicationStartDate, LocalDateTime applicationEndDate, Status status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.coordinator = coordinatorId;
        this.title = title;
        this.companyName = companyName;
        this.description = description;
        this.location = location;
        this.salary = salary;
        this.applicationStartDate = applicationStartDate;
        this.applicationEndDate = applicationEndDate;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CoordinatorEntity getCoordinatorId() {
        return coordinator;
    }

    public void setCoordinatorId(CoordinatorEntity coordinatorId) {
        this.coordinator = coordinatorId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public LocalDateTime getApplicationStartDate() {
        return applicationStartDate;
    }

    public void setApplicationStartDate(LocalDateTime applicationStartDate) {
        this.applicationStartDate = applicationStartDate;
    }

    public LocalDateTime getApplicationEndDate() {
        return applicationEndDate;
    }

    public void setApplicationEndDate(LocalDateTime applicationEndDate) {
        this.applicationEndDate = applicationEndDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
