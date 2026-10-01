package mits.miniproject.universityjobportal.dto.request;

public class AdminRegisterRequest {

    private Long userId;
    private String designation;

    public AdminRegisterRequest() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }
}