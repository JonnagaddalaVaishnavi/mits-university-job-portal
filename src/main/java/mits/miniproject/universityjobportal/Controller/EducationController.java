package mits.miniproject.universityjobportal.Controller;

import mits.miniproject.universityjobportal.Entity.EducationEntity;
import mits.miniproject.universityjobportal.Service.EducationService;
import mits.miniproject.universityjobportal.dto.request.EducationRequest;
import mits.miniproject.universityjobportal.dto.request.EducationUpdateRequest;
import mits.miniproject.universityjobportal.dto.response.EducationResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/education")
public class EducationController {

    private final EducationService educationService;

    public EducationController(EducationService educationService) {
        this.educationService = educationService;
    }

    @PostMapping("/addEducation")
    public ResponseEntity<EducationResponse> addEducation(@RequestBody EducationRequest educationRequest){
        EducationResponse educationEntity = educationService.addEducation(educationRequest);
        return new ResponseEntity<>(educationEntity, HttpStatus.CREATED);
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<EducationResponse>> getByStudent(@PathVariable Long studentId) {
        return new ResponseEntity<>(educationService.getByStudent(studentId), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EducationResponse> update(@PathVariable Long id, @RequestBody EducationUpdateRequest request) {
        return new ResponseEntity<>(educationService.update(id, request), HttpStatus.OK);
    }

    // NEW
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        educationService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
