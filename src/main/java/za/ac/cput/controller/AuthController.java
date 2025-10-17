package za.ac.cput.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Admin;
import za.ac.cput.service.AdminService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/admins")
public class AuthController {

    private final AdminService adminService;
    private final Map<String, Admin> currentUsers = new HashMap<>();

    public AuthController(AdminService adminService) {
        this.adminService = adminService;
    }

    // 🔐 "Login" sets a current user (no password validation here for demo)
    @PostMapping("/login/{id}")
    public ResponseEntity<?> login(@PathVariable Long id) {
        Optional<Admin> user = Optional.ofNullable(adminService.read(id));
        if (user.isEmpty()) {
            return ResponseEntity.badRequest().body("User not found");
        }
        currentUsers.put("activeUser", user.get());
        return ResponseEntity.ok(user.get());
    }

    // Get the current logged-in user, this should read onto the profile from the database
    @GetMapping("/current")
    public ResponseEntity<?> getCurrentUser() {
        Admin current = currentUsers.get("activeUser");
        if (current == null) {
            return ResponseEntity.status(404).body("No current user logged in");
        }
        return ResponseEntity.ok(current);
    }

    // Log out the current user and once user is done with whatever is needed they will then logout
    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        currentUsers.remove("activeUser");
        return ResponseEntity.ok("Logged out successfully");
    }
}
