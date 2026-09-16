package mits.miniproject.universityjobportal.dto.request;

import java.math.BigDecimal;

public class StudentRegisterRequest {
    private Long userId;
    private String usn;
    private String department;
    private BigDecimal cgpa;
    private int backlogs;
    private Integer graduationYear;

    public StudentRegisterRequest() {
    }

    public StudentRegisterRequest(Long userId, String usn, String department, BigDecimal cgpa, int backlogs, Integer graduationYear) {
        this.userId = userId;
        this.usn = usn;
        this.department = department;
        this.cgpa = cgpa;
        this.backlogs = backlogs;
        this.graduationYear = graduationYear;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsn() {
        return usn;
    }

    public void setUsn(String usn) {
        this.usn = usn;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public BigDecimal getCgpa() {
        return cgpa;
    }

    public void setCgpa(BigDecimal cgpa) {
        this.cgpa = cgpa;
    }

    public int getBacklogs() {
        return backlogs;
    }

    public void setBacklogs(int backlogs) {
        this.backlogs = backlogs;
    }

    public Integer getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(Integer graduationYear) {
        this.graduationYear = graduationYear;
    }
}
