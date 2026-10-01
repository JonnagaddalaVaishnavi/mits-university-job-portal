package mits.miniproject.universityjobportal.Controller;

import jakarta.persistence.SqlResultSetMapping;
import mits.miniproject.universityjobportal.Service.JobService;
import mits.miniproject.universityjobportal.dto.request.JobCreateRequest;
import mits.miniproject.universityjobportal.dto.request.JobUpdateRequest;
import mits.miniproject.universityjobportal.dto.response.JobResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {
    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping("/postJob")
    public ResponseEntity<JobResponse> createJob(@RequestBody JobCreateRequest createRequest){
        JobResponse response = jobService.createJob(createRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobResponse> getById(@PathVariable Long id){
        JobResponse response = jobService.getById(id);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    // Student "browse jobs" - only admin-approved jobs
    @GetMapping("/published")
    public ResponseEntity<List<JobResponse>> getPublishedJobs() {
        return new ResponseEntity<>(jobService.getPublishedJobs(), HttpStatus.OK);
    }

    // Admin - jobs waiting for approval
    @GetMapping("/pending")
    public ResponseEntity<List<JobResponse>> getPendingJobs() {
        return new ResponseEntity<>(jobService.getPendingJobs(), HttpStatus.OK);
    }

    // Admin - approve / reject a pending job
    @PatchMapping("/{id}/approve")
    public ResponseEntity<JobResponse> approve(@PathVariable Long id) {
        return new ResponseEntity<>(jobService.approve(id), HttpStatus.OK);
    }

    @PatchMapping("/{id}/reject")
    public ResponseEntity<JobResponse> reject(@PathVariable Long id) {
        return new ResponseEntity<>(jobService.reject(id), HttpStatus.OK);
    }

    // Every job, any status (admin overview)
    @GetMapping("/getAllJobs")
    public ResponseEntity<List<JobResponse>> getAllJobs() {
        return new ResponseEntity<>(jobService.getAllJobs(), HttpStatus.OK);
    }

    // NEW - a coordinator's own posted jobs
    @GetMapping("/coordinator/{coordinatorId}")
    public ResponseEntity<List<JobResponse>> getByCoordinator(@PathVariable Long coordinatorId) {
        return new ResponseEntity<>(jobService.getByCoordinator(coordinatorId), HttpStatus.OK);
    }

    // NEW
    @PutMapping("/{id}")
    public ResponseEntity<JobResponse> update(@PathVariable Long id, @RequestBody JobUpdateRequest request) {
        return new ResponseEntity<>(jobService.update(id, request), HttpStatus.OK);
    }

    // NEW
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        jobService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
