package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Admin;
import za.ac.cput.factory.adminFactory;
import za.ac.cput.service.AdminService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/admins")
public class adminController {


    private final AdminService adminService;
    @Autowired

    public adminController(AdminService adminService) {
        this.adminService = adminService;
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
    private ResponseEntity<String> handleLogin(String token) {
        if ("fail".equals(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
        return ResponseEntity.ok(token);
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
    public void delete(@PathVariable long id) {
        adminService.delete(id);
    }

    @GetMapping("/ping")
    public String ping() {
        return "Admin backend is running!";
    }
}
