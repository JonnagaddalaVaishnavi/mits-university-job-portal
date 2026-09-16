package mits.miniproject.universityjobportal.dto.request;

public class ApplicationRequest {

    private Long studentId; // stand-in for "logged-in student" until security is added
    private Long jobId;

    public ApplicationRequest() {
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }
}
