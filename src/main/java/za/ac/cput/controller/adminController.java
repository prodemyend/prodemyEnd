package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Admin;
import za.ac.cput.factory.adminFactory;
import za.ac.cput.repository.adminRepository;
import za.ac.cput.service.AdminService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/admins")
public class adminController {


    private final AdminService adminService;
    private final adminRepository adminRepository;

    @Autowired

    public adminController(AdminService adminService, adminRepository adminRepository) {
        this.adminService = adminService;
        this.adminRepository = adminRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Admin adminInput) {
        try {
            Admin newAdmin = adminFactory.buildAdmin(
                    adminInput.getFirstName(),
                    adminInput.getLastName(),
                    adminInput.getEmail(),
                    adminInput.getPassword()
            );

            if (newAdmin == null) {
                return ResponseEntity.badRequest().body("Invalid input fields");
            }

            Admin saved = adminService.create(newAdmin);
            return ResponseEntity.ok(saved);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }



    @PostMapping("/login")
    public ResponseEntity<?> handleLogin(@RequestBody Admin loginInput) {
        try {
            Admin admin = adminRepository.findByEmail(loginInput.getEmail());

            if (admin == null || !admin.getPassword().equals(loginInput.getPassword())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("Invalid email or password");
            }

            return ResponseEntity.ok(admin); // returns full Admin object (you can customize this)

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Login error: " + e.getMessage());
        }
    }



    @GetMapping("/all")
    public List<Admin> getAll() {
        return adminService.getAll();
    }

    @GetMapping("/read/{id}")
    public Admin read(@PathVariable Long id) {
        return adminService.read(id);
    }

    @PostMapping("/update")
    public Admin update(@RequestBody Admin admin) {
        return adminService.update(admin);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
        try {
            adminService.delete(id);  // attempt deletion
            return ResponseEntity.ok("Admin deleted successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @GetMapping("/ping")
    public String ping() {
        return "Admin backend is running!";
    }
}
