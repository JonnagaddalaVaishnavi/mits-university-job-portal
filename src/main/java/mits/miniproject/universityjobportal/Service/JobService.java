package mits.miniproject.universityjobportal.Service;

import mits.miniproject.universityjobportal.Entity.CoordinatorEntity;
import mits.miniproject.universityjobportal.Entity.JobEligibilityEntity;
import mits.miniproject.universityjobportal.Entity.JobEntity;
import mits.miniproject.universityjobportal.Repository.ApplicationRepository;
import mits.miniproject.universityjobportal.Repository.CoordinatorRepository;
import mits.miniproject.universityjobportal.Repository.JobEligibilityRepository;
import mits.miniproject.universityjobportal.Repository.JobRepository;
import mits.miniproject.universityjobportal.Utility.Status;
import mits.miniproject.universityjobportal.dto.request.JobCreateRequest;
import mits.miniproject.universityjobportal.dto.request.JobUpdateRequest;
import mits.miniproject.universityjobportal.dto.response.JobEligibilityResponse;
import mits.miniproject.universityjobportal.dto.response.JobResponse;
import mits.miniproject.universityjobportal.exception.NoJobsFoundException;
import mits.miniproject.universityjobportal.exception.InvalidOperationException;
import mits.miniproject.universityjobportal.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobService {

    private final JobRepository jobRepository;
    private final JobEligibilityRepository jobEligibilityRepository;
    private final CoordinatorRepository coordinatorRepository;
    private final ApplicationRepository applicationRepository;


    public JobService(JobRepository jobRepository, JobEligibilityRepository jobEligibilityRepository, CoordinatorRepository coordinatorRepository,ApplicationRepository applicationRepository) {
        this.jobRepository = jobRepository;
        this.jobEligibilityRepository = jobEligibilityRepository;
        this.coordinatorRepository = coordinatorRepository;
        this.applicationRepository = applicationRepository;
    }

    public JobResponse createJob(JobCreateRequest request){
        CoordinatorEntity coordinator = coordinatorRepository.findById(request.getCoordinatorId()).orElseThrow(()-> new ResourceNotFoundException("No coordinator fount with id :"+request.getCoordinatorId()));

        JobEntity entity = new JobEntity();
        entity.setCoordinatorId(coordinator);
        entity.setTitle(request.getTitle());
        entity.setCompanyName(request.getCompanyName());
        entity.setDescription(request.getDescription());
        entity.setLocation(request.getLocation());
        entity.setSalary(request.getSalary());
        entity.setApplicationStartDate(request.getApplicationStartDate());
        entity.setApplicationEndDate(request.getApplicationEndDate());
        entity.setStatus(Status.INPROCESS); // waits for admin approval

        JobEntity saveJob = jobRepository.save(entity);

        JobEligibilityEntity eligibility = new JobEligibilityEntity();
        eligibility.setJob(saveJob);
        eligibility.setMinCgpa(request.getEligibility().getMinCgpa());
        eligibility.setMaxBacklogs(request.getEligibility().getMaxBacklogs());
        eligibility.setGraduationYear(request.getEligibility().getGraduationYear());
        eligibility.setAdditionalCriteria(request.getEligibility().getAdditionalCriteria());
        eligibility.setEligibleDepartments(request.getEligibility().getEligibleDepartments());


        JobEligibilityEntity saveEligibility = jobEligibilityRepository.save(eligibility);

        return mapToResponse(saveJob,saveEligibility);

    }

    public JobResponse getById(Long id){
        JobEntity job = jobRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No job found with id: "+id));
        JobEligibilityEntity eligibility = jobEligibilityRepository.findByJob(job).orElseThrow(() -> new ResourceNotFoundException("No eligibility criteria found for job id: "+id));

        return mapToResponse(job,eligibility);
    }

    // NEW - lists every job, for a student's "browse jobs" screen
    // Every job regardless of status (admin overview)
    public List<JobResponse> getAllJobs() {
        return toResponses(jobRepository.findAll());
    }

    // Only approved jobs - this is what students browse
    public List<JobResponse> getPublishedJobs() {
        return toResponses(jobRepository.findByStatus(Status.PUBLISHED));
    }

    // Jobs waiting for admin approval
    public List<JobResponse> getPendingJobs() {
        return toResponses(jobRepository.findByStatus(Status.INPROCESS));
    }

    public JobResponse approve(Long jobId) {
        return changeApprovalStatus(jobId, Status.PUBLISHED);
    }

    public JobResponse reject(Long jobId) {
        return changeApprovalStatus(jobId, Status.REJECTED);
    }

    private JobResponse changeApprovalStatus(Long jobId, Status newStatus) {
        JobEntity job = jobRepository.findById(jobId)
                .orElseThrow(() -> new ResourceNotFoundException("No job found with id: " + jobId));
        if (job.getStatus() != Status.INPROCESS) {
            throw new InvalidOperationException("Only jobs awaiting approval can be approved or rejected. Current status: " + job.getStatus());
        }
        job.setStatus(newStatus);
        JobEntity saved = jobRepository.save(job);
        return mapToResponse(saved, jobEligibilityRepository.findByJob(saved).orElse(null));
    }

    private List<JobResponse> toResponses(List<JobEntity> jobs) {
        return jobs.stream()
                .map(job -> mapToResponse(job, jobEligibilityRepository.findByJob(job).orElse(null)))
                .collect(Collectors.toList());
    }

    // NEW - a coordinator's own posted jobs, for their "my postings" screen
    public List<JobResponse> getByCoordinator(Long coordinatorId) {
        CoordinatorEntity coordinator = coordinatorRepository.findById(coordinatorId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No coordinator found with id: " + coordinatorId));

        return jobRepository.findByCoordinator(coordinator)
                .stream()
                .map(job -> {
                    JobEligibilityEntity eligibility = jobEligibilityRepository.findByJob(job).orElse(null);
                    return mapToResponse(job, eligibility);
                })
                .collect(Collectors.toList());
    }


    // NEW - updates the job's own fields AND its linked eligibility row's fields in place.
    // Neither the Job nor the JobEligibility row's id changes - we're editing, not replacing.
    public JobResponse update(Long jobId, JobUpdateRequest request) {
        JobEntity job = jobRepository.findById(jobId)
                .orElseThrow(() -> new ResourceNotFoundException("No job found with id: " + jobId));

        job.setTitle(request.getTitle());
        job.setCompanyName(request.getCompanyName());
        job.setDescription(request.getDescription());
        job.setLocation(request.getLocation());
        job.setSalary(request.getSalary());
        job.setApplicationStartDate(request.getApplicationStartDate());
        job.setApplicationEndDate(request.getApplicationEndDate());
        // Status is NOT taken from the request any more - only the admin approve/reject
        // endpoints change it. Editing a rejected job re-submits it for approval.
        if (job.getStatus() == Status.REJECTED) {
            job.setStatus(Status.INPROCESS);
        }
        JobEntity savedJob = jobRepository.save(job);

        JobEligibilityEntity eligibility = jobEligibilityRepository.findByJob(job)
                .orElseThrow(() -> new ResourceNotFoundException("No eligibility criteria found for job id: " + jobId));
        eligibility.setMinCgpa(request.getEligibility().getMinCgpa());
        eligibility.setMaxBacklogs(request.getEligibility().getMaxBacklogs());
        eligibility.setEligibleDepartments(request.getEligibility().getEligibleDepartments());
        eligibility.setGraduationYear(request.getEligibility().getGraduationYear());
        eligibility.setAdditionalCriteria(request.getEligibility().getAdditionalCriteria());
        JobEligibilityEntity savedEligibility = jobEligibilityRepository.save(eligibility);

        return mapToResponse(savedJob, savedEligibility);
    }

    // NEW - deletes the job's dependents FIRST (its eligibility row, and every application
    // against it), then the job itself. Without this order, the delete would fail with a
    // foreign-key constraint violation instead of succeeding.
    public void delete(Long jobId) {
        JobEntity job = jobRepository.findById(jobId)
                .orElseThrow(() -> new ResourceNotFoundException("No job found with id: " + jobId));

        applicationRepository.deleteAll(applicationRepository.findByJob(job));
        jobEligibilityRepository.findByJob(job).ifPresent(jobEligibilityRepository::delete);
        jobRepository.delete(job);
    }
    private JobResponse mapToResponse(JobEntity job, JobEligibilityEntity eligibility) {
        JobEligibilityResponse eligibilityResponse = (eligibility == null) ? null : new JobEligibilityResponse(
                eligibility.getMinCgpa(),
                eligibility.getMaxBacklogs(),
                eligibility.getEligibleDepartments(),
                eligibility.getGraduationYear(),
                eligibility.getAdditionalCriteria()
        );

        return new JobResponse(
                job.getId(),
                job.getCoordinatorId().getUser().getName(),
                job.getCoordinatorId().getDepartment(),
                job.getTitle(),
                job.getCompanyName(),
                job.getDescription(),
                job.getLocation(),
                job.getSalary(),
                job.getApplicationStartDate(),
                job.getApplicationEndDate(),
                job.getStatus(),
                eligibilityResponse
        );
    }
}
