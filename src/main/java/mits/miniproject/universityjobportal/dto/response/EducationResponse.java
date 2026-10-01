package mits.miniproject.universityjobportal.dto.response;

import java.time.LocalDate;

public class EducationResponse {


    private Long id;
    private String degree;
    private String institution;
    private Integer yearOfPassing;

    public EducationResponse() {
    }

    public EducationResponse(Long id, String degree, String institution, Integer yearOfPassing) {
        this.id = id;
        this.degree = degree;
        this.institution = institution;
        this.yearOfPassing = yearOfPassing;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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