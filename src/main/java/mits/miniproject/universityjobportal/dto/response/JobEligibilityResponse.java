package mits.miniproject.universityjobportal.dto.response;

import java.math.BigDecimal;

public class JobEligibilityResponse {

    private BigDecimal minCgpa;
    private Integer maxBacklogs;
    private String eligibleDepartments;
    private Integer graduationYear;
    private String additionalCriteria;

    public JobEligibilityResponse() {
    }

    public JobEligibilityResponse(BigDecimal minCgpa, Integer maxBacklogs,
                                  String eligibleDepartments, Integer graduationYear,
                                  String additionalCriteria) {
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
}