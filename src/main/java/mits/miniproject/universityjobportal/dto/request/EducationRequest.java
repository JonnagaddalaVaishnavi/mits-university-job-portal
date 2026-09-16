package mits.miniproject.universityjobportal.dto.request;

import java.time.LocalDate;

public class EducationRequest {

    private Long studentId;
    private String degree;
    private String institution;
    private LocalDate yearOfPassing;

    public EducationRequest() {
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public LocalDate getYearOfPassing() {
        return yearOfPassing;
    }

    public void setYearOfPassing(LocalDate yearOfPassing) {
        this.yearOfPassing = yearOfPassing;
    }
}