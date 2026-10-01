package mits.miniproject.universityjobportal.Controller;

import mits.miniproject.universityjobportal.Service.ProjectService;
import mits.miniproject.universityjobportal.dto.request.ProjectRequest;
import mits.miniproject.universityjobportal.dto.request.ProjectUpdateRequest;
import mits.miniproject.universityjobportal.dto.response.ProjectResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;


    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping("/addProject")
    public ResponseEntity<ProjectResponse> addProject(@RequestBody ProjectRequest request){
        return new ResponseEntity<>(projectService.addProject(request), HttpStatus.CREATED);
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<ProjectResponse>> getByStudent(@PathVariable Long studentId){
        List<ProjectResponse> response = projectService.getByStudent(studentId);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectResponse> update(@PathVariable Long id, @RequestBody ProjectUpdateRequest request) {
        return new ResponseEntity<>(projectService.update(id, request), HttpStatus.OK);
    }

    // NEW
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        projectService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204
    }
}
