package mits.miniproject.universityjobportal.Service;

import mits.miniproject.universityjobportal.Entity.ApplicationEntity;
import mits.miniproject.universityjobportal.Entity.JobEligibilityEntity;
import mits.miniproject.universityjobportal.Entity.JobEntity;
import mits.miniproject.universityjobportal.Entity.StudentEntity;
import mits.miniproject.universityjobportal.Repository.ApplicationRepository;
import mits.miniproject.universityjobportal.Repository.JobEligibilityRepository;
import mits.miniproject.universityjobportal.Repository.JobRepository;
import mits.miniproject.universityjobportal.Repository.StudentRepository;
import mits.miniproject.universityjobportal.Utility.ApplicationStatus;
import mits.miniproject.universityjobportal.dto.request.ApplicationRequest;
import mits.miniproject.universityjobportal.dto.response.ApplicationResponse;
import mits.miniproject.universityjobportal.exception.DuplicateResourceException;
import mits.miniproject.universityjobportal.exception.NotEligibleException;
import mits.miniproject.universityjobportal.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplicationService {


    private final ApplicationRepository applicationRepository;
    private final StudentRepository studentRepository;
    private final JobRepository jobRepository;
    private final JobEligibilityRepository jobEligibilityRepository;

    public ApplicationService(ApplicationRepository applicationRepository,
                              StudentRepository studentRepository,
                              JobRepository jobRepository,
                              JobEligibilityRepository jobEligibilityRepository) {
        this.applicationRepository = applicationRepository;
        this.studentRepository = studentRepository;
        this.jobRepository = jobRepository;
        this.jobEligibilityRepository = jobEligibilityRepository;
    }

    public ApplicationResponse apply(ApplicationRequest request) {
        // 1. Fetch the Student and Job - both must already exist
        StudentEntity student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No student found with id: " + request.getStudentId()));

        JobEntity job = jobRepository.findById(request.getJobId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No job found with id: " + request.getJobId()));

        // 2. No double-applying - checked BEFORE running eligibility checks.
        if (applicationRepository.existsByStudentAndJob(student, job)) {
            throw new DuplicateResourceException(
                    "Student " + student.getId() + " has already applied to job " + job.getId());
        }

        // 3. Fetch the eligibility rules for this job
        JobEligibilityEntity eligibility = jobEligibilityRepository.findByJob(job)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No eligibility criteria found for job id: " + job.getId()));


        List<String> reasons = new ArrayList<>();

        if (student.getCgpa().compareTo(eligibility.getMinCgpa()) < 0) {
            reasons.add("CGPA " + student.getCgpa() + " is below the required minimum "
                    + eligibility.getMinCgpa());
        }

        if (eligibility.getMaxBacklogs() != null
                && student.getBacklogs() > eligibility.getMaxBacklogs()) {
            reasons.add("Backlogs " + student.getBacklogs() + " exceed the allowed maximum "
                    + eligibility.getMaxBacklogs());
        }

        if (eligibility.getEligibleDepartments() != null
                && !eligibility.getEligibleDepartments().isBlank()) {
            List<String> allowedDepartments = List.of(eligibility.getEligibleDepartments().split(","))
                    .stream().map(String::trim).collect(Collectors.toList());
            if (!allowedDepartments.contains(student.getDepartment())) {
                reasons.add("Department " + student.getDepartment() + " is not eligible for this job");
            }
        }

        if (!student.getGraduationYear().equals(eligibility.getGraduationYear())) {
            throw new NotEligibleException(
                    "Graduation year " + student.getGraduationYear()
                            + " does not match the required year "
                            + eligibility.getGraduationYear()
            );
        }

//        if (eligibility.getGraduationYear() != null
//                && student.getGraduationYear() != null
//                && student.getGraduationYear()!= eligibility.getGraduationYear()) {
//            reasons.add("Graduation year " + student.getGraduationYear()
//                    + " does not match the required year " + eligibility.getGraduationYear());
//        }

        if (!reasons.isEmpty()) {
            throw new NotEligibleException(String.join("; ", reasons));
        }

        // 5. All checks passed - create the application
        ApplicationEntity application = new ApplicationEntity();
        application.setStudent(student);
        application.setJob(job);
        application.setStatus(ApplicationStatus.APPLIED);

        ApplicationEntity savedApplication = applicationRepository.save(application);

        return mapToResponse(savedApplication);
    }

    public List<ApplicationResponse> getByStudent(Long studentId) {
        StudentEntity student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("No student found with id: " + studentId));

        return applicationRepository.findByStudent(student)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public List<ApplicationResponse> getByJob(Long jobId) {
        JobEntity job = jobRepository.findById(jobId)
                .orElseThrow(() -> new ResourceNotFoundException("No job found with id: " + jobId));

        return applicationRepository.findByJob(job)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private ApplicationResponse mapToResponse(ApplicationEntity application) {
        return new ApplicationResponse(
                application.getId(),
                application.getStudent().getUser().getName(),
                application.getStudent().getUsn(),
                application.getJob().getTitle(),
                application.getJob().getCompanyName(),
                application.getStatus(),
                application.getAppliedAt(),
                application.getNotes()
        );
    }
}