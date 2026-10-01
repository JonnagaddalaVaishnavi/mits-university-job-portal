package mits.miniproject.universityjobportal.Controller;

import mits.miniproject.universityjobportal.Entity.DocumentEntity;
import mits.miniproject.universityjobportal.Service.DocumentService;
import mits.miniproject.universityjobportal.dto.request.DocumentRequest;
import mits.miniproject.universityjobportal.dto.request.DocumentUpdateRequest;
import mits.miniproject.universityjobportal.dto.response.DocumentResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {

    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @PostMapping("/addDocument")
    public ResponseEntity<DocumentResponse> addDocument(@RequestBody DocumentRequest request){
        DocumentResponse document = documentService.addDocument(request);
        return new ResponseEntity<>(document, HttpStatus.CREATED);

    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<DocumentResponse>> getByStudent(@PathVariable Long studentId) {
        return new ResponseEntity<>(documentService.getByStudent(studentId), HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<DocumentResponse> update(@PathVariable Long id, @RequestBody DocumentUpdateRequest request) {
        return new ResponseEntity<>(documentService.update(id, request), HttpStatus.OK);
    }

    // NEW
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        documentService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
