package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Enrollment;
import za.ac.cput.service.EnrollmentService;

import java.util.List;
import java.util.Optional;

import static za.ac.cput.domain.Enrollment.Status.APPROVED;
import static za.ac.cput.domain.Enrollment.Status.REJECTED;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @Autowired
    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    // Create new enrollment
    @PostMapping
    public ResponseEntity<Enrollment> createEnrollment(@RequestBody Enrollment enrollment) {
        Enrollment savedEnrollment = enrollmentService.create(enrollment);
        return ResponseEntity.ok(savedEnrollment);
    }

    // Get enrollment by ID
    @GetMapping("/{id}")
    public ResponseEntity<Enrollment> getEnrollment(@PathVariable Long id) {
        Optional<Enrollment> enrollment = enrollmentService.read(id);
        return enrollment.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get all enrollments
    @GetMapping
    public List<Enrollment> getAllEnrollments() {
        return enrollmentService.getAll();
    }

    // Approve enrollment
    @PutMapping("/{id}/approve")
    public ResponseEntity<Enrollment> approveEnrollment(@PathVariable Long id) {
        Optional<Enrollment> enrollment = enrollmentService.read(id);
        if (enrollment.isPresent()) {
            Enrollment e = enrollment.get();
            e.setStatus(APPROVED);
            return ResponseEntity.ok(enrollmentService.update(e));
        }
        return ResponseEntity.notFound().build();
    }

    // Reject enrollment
    @PutMapping("/{id}/reject")
    public ResponseEntity<Enrollment> rejectEnrollment(@PathVariable Long id) {
        Optional<Enrollment> enrollment = enrollmentService.read(id);
        if (enrollment.isPresent()) {
            Enrollment e = enrollment.get();
            e.setStatus(REJECTED);
            return ResponseEntity.ok(enrollmentService.update(e));
        }
        return ResponseEntity.notFound().build();
    }
}
