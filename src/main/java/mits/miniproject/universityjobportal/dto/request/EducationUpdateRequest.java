package mits.miniproject.universityjobportal.dto.request;

import java.time.LocalDate;

public class EducationUpdateRequest {

    private String degree;
    private String institution;
    private Integer yearOfPassing;

    public EducationUpdateRequest() {
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

    public Integer getYearOfPassing() {
        return yearOfPassing;
    }

    public void setYearOfPassing(Integer yearOfPassing) {
        this.yearOfPassing = yearOfPassing;
    }
}