package za.ac.cput.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Admin;
import za.ac.cput.service.AdminService;

import java.util.List;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:8080")
public class adminController {

    private final AdminService service;

    @Autowired
    public adminController(AdminService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<Admin> addAdmin(@RequestBody Admin admin) {
        Admin createdAdmin = service.create(admin);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAdmin);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<Admin> read(@PathVariable Long id) {
        Admin admin = service.read(id);
        return (admin != null)
                ? ResponseEntity.ok(admin)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PutMapping("/update")
    public ResponseEntity<Admin> update(@RequestBody Admin admin) {
        Admin updatedAdmin = service.update(admin);
        return (updatedAdmin != null)
                ? ResponseEntity.ok(updatedAdmin)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        try {
            service.delete(id);
            return ResponseEntity.ok("Admin successfully deleted!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Admin ID not found.");
        }
    }

    @GetMapping("/allAdmins")
    public ResponseEntity<List<Admin>> getAll() {
        List<Admin> admins = service.getAll();
        return ResponseEntity.ok(admins);
    }
}

