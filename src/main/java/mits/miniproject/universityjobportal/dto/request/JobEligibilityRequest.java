package mits.miniproject.universityjobportal.dto.request;

import java.math.BigDecimal;
import java.time.LocalDate;

public class JobEligibilityRequest {
    private BigDecimal minCgpa;
    private int maxBacklogs;
    private String eligibleDepartments;
    private Integer graduationYear;
    private String additionalCriteria;

    public JobEligibilityRequest() {
    }

    public JobEligibilityRequest(BigDecimal minCgpa, int maxBacklogs, String eligibleDepartments, Integer graduationYear, String additionalCriteria) {
        this.minCgpa = minCgpa;
        this.maxBacklogs = maxBacklogs;
        this.eligibleDepartments = eligibleDepartments;
        this.graduationYear = graduationYear;
        this.additionalCriteria = additionalCriteria;
    }

    public BigDecimal getMinCgpa() {
        return minCgpa;
    }

    public void setMinCgpa(BigDecimal minCgpa) {
        this.minCgpa = minCgpa;
    }

    public int getMaxBacklogs() {
        return maxBacklogs;
    }

    public void setMaxBacklogs(int maxBacklogs) {
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
}
