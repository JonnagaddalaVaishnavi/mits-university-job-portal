package mits.miniproject.universityjobportal.dto.request;

import mits.miniproject.universityjobportal.Utility.ApplicationStatus;

public class ApplicationStatusUpdateRequest {

    private ApplicationStatus status;
    private String notes;

    public ApplicationStatusUpdateRequest() {
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}