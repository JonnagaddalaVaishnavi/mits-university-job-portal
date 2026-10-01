package mits.miniproject.universityjobportal.Service;

import mits.miniproject.universityjobportal.Entity.AdminEntity;
import mits.miniproject.universityjobportal.Entity.UserEntity;
import mits.miniproject.universityjobportal.Repository.AdminRepository;
import mits.miniproject.universityjobportal.Repository.UserRepository;
import mits.miniproject.universityjobportal.dto.request.AdminRegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private UserRepository userRepository;


    public AdminEntity registerAdmin(AdminRegisterRequest request) {

        // Find the existing user
        UserEntity user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException(
                        "User not found with id: " + request.getUserId()
                ));

        // Create Admin entity
        AdminEntity admin = new AdminEntity();

        // Because @MapsId is used, the admin ID comes from the UserEntity
        admin.setId(request.getUserId());

        admin.setUser(user);
        admin.setDesignation(request.getDesignation());

        return adminRepository.save(admin);
    }


    public AdminEntity getAdminById(Long id) {

        return adminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Admin not found with id: " + id
                ));
    }
}