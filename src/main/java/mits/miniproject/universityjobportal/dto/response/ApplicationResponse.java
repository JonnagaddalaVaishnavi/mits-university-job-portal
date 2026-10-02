package mits.miniproject.universityjobportal.dto.response;

import mits.miniproject.universityjobportal.Utility.ApplicationStatus;

import java.time.LocalDateTime;

public class ApplicationResponse {


    private Long id;
    private Long jobId;
    private String studentName;
    private String usn;
    private String jobTitle;
    private String companyName;
    private ApplicationStatus status;
    private LocalDateTime appliedAt;
    private String notes;

    public ApplicationResponse() {
    }

    public ApplicationResponse(Long id, Long jobId, String studentName, String usn, String jobTitle,
                               String companyName, ApplicationStatus status,
                               LocalDateTime appliedAt, String notes) {
        this.id = id;
        this.jobId = jobId;
        this.studentName = studentName;
        this.usn = usn;
        this.jobTitle = jobTitle;
        this.companyName = companyName;
        this.status = status;
        this.appliedAt = appliedAt;
        this.notes = notes;
    }

    public Long getId() {
        return id;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getUsn() {
        return usn;
    }

    public void setUsn(String usn) {
        this.usn = usn;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }

    public LocalDateTime getAppliedAt() {
        return appliedAt;
    }

    public void setAppliedAt(LocalDateTime appliedAt) {
        this.appliedAt = appliedAt;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}