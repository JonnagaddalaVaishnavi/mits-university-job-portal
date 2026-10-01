package mits.miniproject.universityjobportal.dto.request;

import mits.miniproject.universityjobportal.Utility.Status;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class JobUpdateRequest {

    private String title;
    private String companyName;
    private String description;
    private String location;
    private BigDecimal salary;
    private LocalDateTime applicationStartDate;
    private LocalDateTime applicationEndDate;
    private Status status; // lets a coordinator close/reopen a posting
    private JobEligibilityRequest eligibility;

    public JobUpdateRequest() {
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

    public JobEligibilityRequest getEligibility() {
        return eligibility;
    }

    public void setEligibility(JobEligibilityRequest eligibility) {
        this.eligibility = eligibility;
    }
}