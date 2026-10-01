package mits.miniproject.universityjobportal.Controller;

import mits.miniproject.universityjobportal.Entity.AdminEntity;
import mits.miniproject.universityjobportal.Service.AdminService;
import mits.miniproject.universityjobportal.dto.request.AdminRegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admins")
@CrossOrigin(origins = "http://localhost:5173")
public class AdminController {

    @Autowired
    private AdminService adminService;


    @PostMapping("/register")
    public ResponseEntity<AdminEntity> registerAdmin(
            @RequestBody AdminRegisterRequest request) {

        AdminEntity admin = adminService.registerAdmin(request);

        return new ResponseEntity<>(admin, HttpStatus.CREATED);
    }


    @GetMapping("/getAdminById/{id}")
    public ResponseEntity<AdminEntity> getAdminById(
            @PathVariable Long id) {

        AdminEntity admin = adminService.getAdminById(id);

        return ResponseEntity.ok(admin);
    }
}