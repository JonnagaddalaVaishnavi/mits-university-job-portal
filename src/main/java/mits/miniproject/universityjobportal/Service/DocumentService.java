package mits.miniproject.universityjobportal.Service;

import mits.miniproject.universityjobportal.Entity.DocumentEntity;
import mits.miniproject.universityjobportal.Entity.StudentEntity;
import mits.miniproject.universityjobportal.Repository.DocumentRepository;
import mits.miniproject.universityjobportal.Repository.StudentRepository;
import mits.miniproject.universityjobportal.dto.request.DocumentRequest;
import mits.miniproject.universityjobportal.dto.request.DocumentUpdateRequest;
import mits.miniproject.universityjobportal.dto.response.DocumentResponse;
import mits.miniproject.universityjobportal.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocumentService {

    private final DocumentRepository documentRepository;
    private final StudentRepository studentRepository;

    public DocumentService(DocumentRepository documentRepository, StudentRepository studentRepository) {
        this.documentRepository = documentRepository;
        this.studentRepository = studentRepository;
    }

    public DocumentResponse addDocument(DocumentRequest request) {
        StudentEntity student = studentRepository.findById(request.getStudentId()).orElseThrow(() -> new ResourceNotFoundException("No student found with this id"));

        DocumentEntity document = new DocumentEntity();
        document.setStudent(student);
        document.setDocumentType(request.getDocumentType());
        document.setFileName(request.getFileName());
        document.setFilePath(request.getFilePath());

        DocumentEntity saved = documentRepository.save(document);
        return mapToResponse(saved);
    }

    public List<DocumentResponse> getByStudent(Long studentId) {

        StudentEntity student = studentRepository.findById(studentId).orElseThrow(() -> new ResourceNotFoundException("No student found with this id: " + studentId));

        return documentRepository.findByStudent(student)
                .stream().map(this::mapToResponse).collect(Collectors.toList());
    }


    // NEW
    public DocumentResponse update(Long documentId, DocumentUpdateRequest request) {
        DocumentEntity document = documentRepository.findById(documentId)
                .orElseThrow(() -> new ResourceNotFoundException("No document found with id: " + documentId));

        document.setDocumentType(request.getDocumentType());
        document.setFileName(request.getFileName());
        document.setFilePath(request.getFilePath());

        return mapToResponse(documentRepository.save(document));
    }

    // NEW
    public void delete(Long documentId) {
        if (!documentRepository.existsById(documentId)) {
            throw new ResourceNotFoundException("No document found with id: " + documentId);
        }
        documentRepository.deleteById(documentId);
    }

    private DocumentResponse mapToResponse(DocumentEntity document) {
        return new DocumentResponse(
                document.getId(),
                document.getDocumentType(),
                document.getFileName(),
                document.getFilePath(),
                document.getUploadedAt()
        );

    }
}
