package mits.miniproject.universityjobportal.Service;

import mits.miniproject.universityjobportal.Entity.EducationEntity;
import mits.miniproject.universityjobportal.Entity.StudentEntity;
import mits.miniproject.universityjobportal.Repository.EducationRepository;
import mits.miniproject.universityjobportal.Repository.StudentRepository;
import mits.miniproject.universityjobportal.dto.request.EducationRequest;
import mits.miniproject.universityjobportal.dto.response.EducationResponse;
import mits.miniproject.universityjobportal.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EducationService {

    private final EducationRepository educationRepository;
    private final StudentRepository studentRepository;

    public EducationService(EducationRepository educationRepository, StudentRepository studentRepository) {
        this.educationRepository = educationRepository;
        this.studentRepository = studentRepository;
    }

    public EducationResponse addEducation(EducationRequest educationRequest){
//        StudentEntity student = educationRepository.findById(educationRequest.getStudentId()).orElseThrow(()-> new ResourceNotFoundException("No student found with id: "+educationRequest.getStudentId()));

          StudentEntity student = studentRepository.findById(educationRequest.getStudentId()).orElseThrow(() -> new ResourceNotFoundException(
                            "No student found with id: " + educationRequest.getStudentId()));

        EducationEntity education = new EducationEntity();
        education.setStudent(student);
        education.setDegree(educationRequest.getDegree());
        education.setInstitution(educationRequest.getInstitution());
        education.setYearOfPassing(educationRequest.getYearOfPassing());

        EducationEntity saved = educationRepository.save(education);
        return mapToResponse(saved);
    }

    public List<EducationResponse> getByStudent(Long studentId) {
        StudentEntity student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("No student found with id: " + studentId));

        return educationRepository.findByStudent(student)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private EducationResponse mapToResponse(EducationEntity education) {
        return new EducationResponse(
                education.getId(),
                education.getDegree(),
                education.getInstitution(),
                education.getYearOfPassing()
        );
    }
}
