package mits.miniproject.universityjobportal.dto.response;

import java.time.LocalDateTime;

public class DocumentResponse {


    private Long id;
    private String documentType;
    private String fileName;
    private String filePath;
    private LocalDateTime uploadedAt;

    public DocumentResponse() {
    }

    public DocumentResponse(Long id, String documentType, String fileName, String filePath,
                            LocalDateTime uploadedAt) {
        this.id = id;
        this.documentType = documentType;
        this.fileName = fileName;
        this.filePath = filePath;
        this.uploadedAt = uploadedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public LocalDateTime getUploadedAt() {
        return uploadedAt;
    }

    public void setUploadedAt(LocalDateTime uploadedAt) {
        this.uploadedAt = uploadedAt;
    }
}