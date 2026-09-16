package mits.miniproject.universityjobportal.Entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class JobEligibilityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "job_id", nullable = false, unique = true)
    private JobEntity job;

    @Column(nullable = false)
    private BigDecimal minCgpa;

    private Integer maxBacklogs;

    private String eligibleDepartments;

    private Integer graduationYear;

    private String additionalCriteria;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public JobEligibilityEntity() {
    }

    public JobEligibilityEntity(Long id, JobEntity job, BigDecimal minCgpa, Integer maxBacklogs, String eligibleDepartments, Integer graduationYear, String additionalCriteria, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.job = job;
        this.minCgpa = minCgpa;
        this.maxBacklogs = maxBacklogs;
        this.eligibleDepartments = eligibleDepartments;
        this.graduationYear = graduationYear;
        this.additionalCriteria = additionalCriteria;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public JobEntity getJob() {
        return job;
    }

    public void setJob(JobEntity job) {
        this.job = job;
    }

    public BigDecimal getMinCgpa() {
        return minCgpa;
    }

    public void setMinCgpa(BigDecimal minCgpa) {
        this.minCgpa = minCgpa;
    }

    public Integer getMaxBacklogs() {
        return maxBacklogs;
    }

    public void setMaxBacklogs(Integer maxBacklogs) {
        this.maxBacklogs = maxBacklogs;
    }

    public String getEligibleDepartments() {
        return eligibleDepartments;
    }

    public void setEligibleDepartments(String eligibleDepartments) {
        this.eligibleDepartments = eligibleDepartments;
    }

    public Integer getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(Integer graduationYear) {
        this.graduationYear = graduationYear;
    }

    public String getAdditionalCriteria() {
        return additionalCriteria;
    }

    public void setAdditionalCriteria(String additionalCriteria) {
        this.additionalCriteria = additionalCriteria;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
