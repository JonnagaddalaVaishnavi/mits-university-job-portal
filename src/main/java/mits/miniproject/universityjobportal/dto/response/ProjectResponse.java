package mits.miniproject.universityjobportal.dto.response;

import java.time.LocalDate;

public class ProjectResponse {
    private Long id;
    private String title;
    private String description;
    private String technologies;
    private LocalDate startDate;
    private LocalDate endDate;

    public ProjectResponse() {
    }

    public ProjectResponse(Long id, String title, String description, String technologies,
                           LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.technologies = technologies;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTechnologies() {
        return technologies;
    }

    public void setTechnologies(String technologies) {
        this.technologies = technologies;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}