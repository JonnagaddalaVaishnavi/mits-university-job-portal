package mits.miniproject.universityjobportal.Controller;

import mits.miniproject.universityjobportal.Entity.CoordinatorEntity;
import mits.miniproject.universityjobportal.Service.CoordinatorService;
import mits.miniproject.universityjobportal.dto.request.CoordinatorRegisterRequest;
import mits.miniproject.universityjobportal.dto.response.CoordinatorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/coordinators")
public class CoordinatorController {

    private final CoordinatorService coordinatorService;

    public CoordinatorController(CoordinatorService coordinatorService) {
        this.coordinatorService = coordinatorService;
    }

    @PostMapping("/register")
    public ResponseEntity<CoordinatorResponse> register(@RequestBody CoordinatorRegisterRequest request){
        CoordinatorResponse response = coordinatorService.register(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CoordinatorResponse> getById(@PathVariable Long id){
        return new ResponseEntity<>(coordinatorService.getById(id),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CoordinatorResponse> update(
            @PathVariable Long id,
            @RequestBody CoordinatorRegisterRequest request) {

        CoordinatorResponse response =
                coordinatorService.update(id, request);

        return ResponseEntity.ok(response);
    }
}
