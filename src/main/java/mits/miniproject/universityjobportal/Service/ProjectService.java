package mits.miniproject.universityjobportal.Service;

import mits.miniproject.universityjobportal.Entity.ProjectEntity;
import mits.miniproject.universityjobportal.Entity.StudentEntity;
import mits.miniproject.universityjobportal.Repository.ProjectRepository;
import mits.miniproject.universityjobportal.Repository.StudentRepository;
import mits.miniproject.universityjobportal.dto.request.ProjectRequest;
import mits.miniproject.universityjobportal.dto.response.ProjectResponse;
import mits.miniproject.universityjobportal.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final StudentRepository studentRepository;

    public ProjectService(ProjectRepository projectRepository, StudentRepository studentRepository) {
        this.projectRepository = projectRepository;
        this.studentRepository = studentRepository;
    }

    public ProjectResponse addProject(ProjectRequest request){
        StudentEntity student = studentRepository.findById(request.getStudentId()).orElseThrow(()-> new ResourceNotFoundException("No student found with id: "+request.getStudentId()));

        ProjectEntity project = new ProjectEntity();
        project.setStudent(student);
        project.setTitle(request.getTitle());
        project.setDescription(request.getDescription());
        project.setTechnologies(request.getTechnologies());
        project.setStartDate(request.getStartDate());
        project.setEndDate(request.getEndDate());

        ProjectEntity saved = projectRepository.save(project);
        return mapToResponse(saved);
    }

    public List<ProjectResponse> getByStudent(Long studentId){
        StudentEntity student = studentRepository.findById(studentId).orElseThrow(()-> new ResourceNotFoundException("No student found with id: "+studentId));

        return projectRepository.findByStudent(student).stream().map(this::mapToResponse).collect(Collectors
                .toList());
    }
    private ProjectResponse mapToResponse(ProjectEntity project) {
        return new ProjectResponse(
                project.getId(),
                project.getTitle(),
                project.getDescription(),
                project.getTechnologies(),
                project.getStartDate(),
                project.getEndDate()
        );
    }
}

