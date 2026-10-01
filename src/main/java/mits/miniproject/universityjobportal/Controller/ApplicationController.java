
package mits.miniproject.universityjobportal.Controller;

import mits.miniproject.universityjobportal.dto.request.ApplicationRequest;
import mits.miniproject.universityjobportal.dto.request.ApplicationStatusUpdateRequest;
import mits.miniproject.universityjobportal.dto.response.ApplicationResponse;
import mits.miniproject.universityjobportal.Service.ApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping("/apply")
    public ResponseEntity<ApplicationResponse> apply(@RequestBody ApplicationRequest request) {
        ApplicationResponse response = applicationService.apply(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED); // 201
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<ApplicationResponse>> getByStudent(@PathVariable Long studentId) {
        return new ResponseEntity<>(applicationService.getByStudent(studentId), HttpStatus.OK);
    }

    @GetMapping("/job/{jobId}")
    public ResponseEntity<List<ApplicationResponse>> getByJob(@PathVariable Long jobId) {
        return new ResponseEntity<>(applicationService.getByJob(jobId), HttpStatus.OK);
    }
    // NEW - coordinator updates status/notes only
    @PatchMapping("/{id}/status")
    public ResponseEntity<ApplicationResponse> updateStatus(@PathVariable Long id,
                                                            @RequestBody ApplicationStatusUpdateRequest request) {
        return new ResponseEntity<>(applicationService.updateStatus(id, request), HttpStatus.OK);
    }

    // NEW - student withdraws their application
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        applicationService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
