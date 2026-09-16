package mits.miniproject.universityjobportal.dto.request;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class JobPostRequest {
    private Long coordinatorId;
    private String title;
    private String companyName;
    private String description;
    private String location;
    private BigDecimal salary;
    private LocalDateTime applicationStartDate;
    private LocalDateTime applicationEndDate;

    public JobPostRequest(Long coordinatorId, String title, String companyName, String description, String location, BigDecimal salary, LocalDateTime applicationStartDate, LocalDateTime applicationEndDate) {
        this.coordinatorId = coordinatorId;
        this.title = title;
        this.companyName = companyName;
        this.description = description;
        this.location = location;
        this.salary = salary;
        this.applicationStartDate = applicationStartDate;
        this.applicationEndDate = applicationEndDate;
    }

    public JobPostRequest() {
    }

    public Long getCoordinatorId() {
        return coordinatorId;
    }

    public void setCoordinatorId(Long coordinatorId) {
        this.coordinatorId = coordinatorId;
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
}
