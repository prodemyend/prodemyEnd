package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.DTO.EnrollmentDTO;
import za.ac.cput.domain.Enrollment;
import za.ac.cput.service.EnrollmentService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public ResponseEntity<EnrollmentDTO> createEnrollment(@RequestBody Enrollment enrollment) {
        Enrollment savedEnrollment = enrollmentService.create(enrollment);
        EnrollmentDTO dto = enrollmentService.toDTO(savedEnrollment);
        return ResponseEntity.ok(dto);
    }

    // Get enrollment by ID
    @GetMapping("/{id}")
    public ResponseEntity<EnrollmentDTO> getEnrollment(@PathVariable Long id) {
        Optional<Enrollment> enrollment = enrollmentService.read(id);
        return enrollment
                .map(enr -> ResponseEntity.ok(enrollmentService.toDTO(enr)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get all enrollments
    @GetMapping
    public List<EnrollmentDTO> getAllEnrollments() {
        List<Enrollment> enrollments = enrollmentService.getAll();
        return enrollments.stream()
                .map(enrollmentService::toDTO)
                .collect(Collectors.toList());
    }

    // Approve enrollment
    @PutMapping("/{id}/approve")
    public ResponseEntity<EnrollmentDTO> approveEnrollment(@PathVariable Long id) {
        Optional<Enrollment> enrollment = enrollmentService.read(id);
        if (enrollment.isPresent()) {
            Enrollment e = enrollment.get();
            e.setStatus(Enrollment.Status.APPROVED);
            Enrollment updated = enrollmentService.update(e);
            return ResponseEntity.ok(enrollmentService.toDTO(updated));
        }
        return ResponseEntity.notFound().build();
    }

    // Reject enrollment
    @PutMapping("/{id}/reject")
    public ResponseEntity<EnrollmentDTO> rejectEnrollment(@PathVariable Long id) {
        Optional<Enrollment> enrollment = enrollmentService.read(id);
        if (enrollment.isPresent()) {
            Enrollment e = enrollment.get();
            e.setStatus(Enrollment.Status.REJECTED);
            Enrollment updated = enrollmentService.update(e);
            return ResponseEntity.ok(enrollmentService.toDTO(updated));
        }
        return ResponseEntity.notFound().build();
    }
}
