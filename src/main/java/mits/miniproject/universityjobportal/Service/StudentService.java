package mits.miniproject.universityjobportal.Service;

import mits.miniproject.universityjobportal.Entity.StudentEntity;
import mits.miniproject.universityjobportal.Entity.UserEntity;
import mits.miniproject.universityjobportal.Repository.*;
import mits.miniproject.universityjobportal.Utility.Role;
import mits.miniproject.universityjobportal.dto.request.StudentRegisterRequest;
import mits.miniproject.universityjobportal.dto.request.StudentUpdateRequest;
import mits.miniproject.universityjobportal.dto.response.StudentResponse;
import mits.miniproject.universityjobportal.exception.DuplicateResourceException;
import mits.miniproject.universityjobportal.exception.InvalidRoleException;
import mits.miniproject.universityjobportal.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final UserRepository userRepository;
    private final ApplicationRepository applicationRepository;
    private final ProjectRepository projectRepository;
    private final DocumentRepository documentRepository;
    private final EducationRepository educationRepository;

    public StudentService(StudentRepository studentRepository, UserRepository userRepository, ApplicationRepository applicationRepository, ProjectRepository projectRepository, DocumentRepository documentRepository, EducationRepository educationRepository) {
        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
        this.applicationRepository = applicationRepository;
        this.projectRepository = projectRepository;
        this.documentRepository = documentRepository;
        this.educationRepository = educationRepository;
    }


    public StudentResponse studentRegister(StudentRegisterRequest registerRequest){
        UserEntity user = userRepository.findById(registerRequest.getUserId()).orElseThrow(()-> new ResourceNotFoundException("No user found with id: "+registerRequest.getUserId()));
        if(user.getRole() != Role.STUDENT){
            throw new InvalidRoleException("User with id "+registerRequest.getUserId()+" is not registered as student");
        }
        if(studentRepository.findById(registerRequest.getUserId()).isPresent()){
            throw new DuplicateResourceException("A student profile with id "+registerRequest.getUserId()+" already exists");
        }

        StudentEntity student = new StudentEntity();
        student.setUser(user);
        student.setUsn(registerRequest.getUsn());
        student.setDepartment(registerRequest.getDepartment());
        student.setCgpa(registerRequest.getCgpa());
        student.setBacklogs(registerRequest.getBacklogs());
        student.setGraduationYear(registerRequest.getGraduationYear());

        StudentEntity savedStudent = studentRepository.save(student);

        return mapToResponse(savedStudent);
    }

    public StudentResponse getByID(Long id){
        StudentEntity entity = studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No student found with id: "+id ));
        return mapToResponse(entity);
    }


    // NEW - updates the academic fields only; usn/department/cgpa/backlogs/graduationYear
    // are the only things that ever change here - identity (id, linked User) never does.
    public StudentResponse update(Long id, StudentUpdateRequest request) {
        StudentEntity student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No student found with id: " + id));

        student.setUsn(request.getUsn());
        student.setDepartment(request.getDepartment());
        student.setCgpa(request.getCgpa());
        student.setBacklogs(request.getBacklogs());
        student.setGraduationYear(request.getGraduationYear());

        return mapToResponse(studentRepository.save(student));
    }

    // NEW - deletes every dependent row across four other tables FIRST, since none of
    // those relationships are configured with cascade delete. Order matters here only
    // in that all four must happen before the StudentEntity delete itself.
    public void delete(Long id) {
        StudentEntity student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No student found with id: " + id));

        applicationRepository.deleteAll(applicationRepository.findByStudent(student));
        projectRepository.deleteAll(projectRepository.findByStudent(student));
        documentRepository.deleteAll(documentRepository.findByStudent(student));
        educationRepository.deleteAll(educationRepository.findByStudent(student));

        studentRepository.delete(student);
        // Note: the underlying User row is intentionally left behind - deleting a User
        // is a separate, deliberately-excluded decision (see the Auth module).
    }

    private StudentResponse mapToResponse(StudentEntity student) {
        UserEntity user = student.getUser();
        return new StudentResponse(
                student.getId(),
                user.getName(),
                user.getEmail(),
                user.getPhone(),
                student.getUsn(),
                student.getDepartment(),
                student.getCgpa(),
                student.getBacklogs(),
                student.getGraduationYear()
        );
    }
}
